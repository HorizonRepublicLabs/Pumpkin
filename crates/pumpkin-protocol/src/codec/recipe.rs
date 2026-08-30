use pumpkin_data::recipes::RecipeCategoryTypes;

use pumpkin_data::item::Item;
use pumpkin_data::tag::Taggable;

#[derive(Clone, Debug)]
pub enum OwnedRecipeIngredient {
    Simple(String),
    Tagged(String),
    OneOf(Vec<String>),
}

impl OwnedRecipeIngredient {
    #[must_use]
    pub fn match_item(&self, item: &Item) -> bool {
        // A vanilla item's registry key is bare; a runtime-registered one is already
        // namespaced. Prefixing unconditionally turned every mod item into
        // "minecraft:mod:thing" and matched nothing.
        let name = if item.registry_key.contains(':') {
            item.registry_key.to_string()
        } else {
            format!("minecraft:{}", item.registry_key)
        };
        match self {
            Self::Simple(id) => name == *id,
            // The generated tables answer for vanilla tags; the dynamic store answers
            // for tags the mod datapacks define (and their additions to `c:` tags).
            Self::Tagged(tag) => {
                item.is_tagged_with(tag).unwrap_or(false)
                    || pumpkin_data::dynamic::item_tag_contains(tag, &name)
            }
            Self::OneOf(ids) => ids.contains(&name),
        }
    }
}

#[derive(Clone, Debug)]
pub struct OwnedRecipeResult {
    pub item_id: String,
    pub count: u8,
    // TODO: Add components/enchantments if needed for the display result
}

#[derive(Clone, Debug)]
pub enum OwnedCraftingRecipe {
    Shaped {
        recipe_id: Option<String>,
        category: RecipeCategoryTypes,
        group: Option<String>,
        show_notification: bool,
        key: Vec<(char, OwnedRecipeIngredient)>,
        pattern: Vec<String>,
        result: OwnedRecipeResult,
    },
    Shapeless {
        recipe_id: Option<String>,
        category: RecipeCategoryTypes,
        group: Option<String>,
        ingredients: Vec<OwnedRecipeIngredient>,
        result: OwnedRecipeResult,
    },
}

#[derive(Clone, Debug)]
pub struct OwnedCookingRecipe {
    pub recipe_id: String,
    pub category: RecipeCategoryTypes,
    pub group: Option<String>,
    pub ingredient: OwnedRecipeIngredient,
    pub cooking_time: i32,
    pub experience: f32,
    pub result: OwnedRecipeResult,
}

#[derive(Clone, Debug)]
pub enum OwnedCookingRecipeType {
    Blasting(OwnedCookingRecipe),
    Smelting(OwnedCookingRecipe),
    Smoking(OwnedCookingRecipe),
    CampfireCooking(OwnedCookingRecipe),
}

#[derive(Clone, Debug)]
pub enum DynamicRecipe {
    Crafting(OwnedCraftingRecipe),
    Cooking(OwnedCookingRecipeType),
}

#[cfg(test)]
mod tests {
    use std::collections::HashMap;

    use pumpkin_data::item::Item;

    use super::OwnedRecipeIngredient;

    #[test]
    fn a_mod_item_matches_by_its_namespaced_id() {
        // The regression: unconditional "minecraft:" prefixing broke every mod-item
        // ingredient. A vanilla item still matches through the prefix.
        let gold = Item::from_registry_key("gold_ingot").expect("gold exists");
        assert!(OwnedRecipeIngredient::Simple("minecraft:gold_ingot".into()).match_item(gold));
        assert!(!OwnedRecipeIngredient::Simple("minecraft:iron_ingot".into()).match_item(gold));
    }

    #[test]
    fn a_datapack_tag_answers_for_tagged_ingredients() {
        let mut tags = HashMap::new();
        tags.insert(
            "c:test_ingots".to_string(),
            vec!["minecraft:gold_ingot".to_string()],
        );
        pumpkin_data::dynamic::install_item_tags(tags);

        let gold = Item::from_registry_key("gold_ingot").expect("gold exists");
        let iron = Item::from_registry_key("iron_ingot").expect("iron exists");
        assert!(OwnedRecipeIngredient::Tagged("c:test_ingots".into()).match_item(gold));
        assert!(!OwnedRecipeIngredient::Tagged("c:test_ingots".into()).match_item(iron));
    }
}
