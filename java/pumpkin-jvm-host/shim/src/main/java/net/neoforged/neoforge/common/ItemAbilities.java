package net.neoforged.neoforge.common;

import java.util.Set;
import dev.pumpkin.shim.Unimplemented;

public class ItemAbilities {

    public static final ItemAbility SHEARS_DIG = ItemAbility.get("shears_dig");

    public static final ItemAbility AXE_STRIP = ItemAbility.get("axe_strip");

    public static final ItemAbility AXE_SCRAPE = ItemAbility.get("axe_scrape");

    public static final ItemAbility AXE_WAX_OFF = ItemAbility.get("axe_wax_off");

    public static final ItemAbility SHOVEL_FLATTEN = ItemAbility.get("shovel_flatten");

    public static final ItemAbility SHOVEL_DOUSE = ItemAbility.get("shovel_douse");

    public static final ItemAbility SHEARS_DISARM = ItemAbility.get("shears_disarm");

    public static final ItemAbility SHEARS_TRIM = ItemAbility.get("shears_trim");

    public static final ItemAbility HOE_TILL = ItemAbility.get("till");

    public static final ItemAbility FIRESTARTER_LIGHT = ItemAbility.get("firestarter_light");

    public static final Set<ItemAbility> DEFAULT_AXE_ACTIONS = Set.of(ItemAbility.get("axe_strip"), ItemAbility.get("axe_scrape"), ItemAbility.get("axe_wax_off"));

    public static final Set<ItemAbility> DEFAULT_HOE_ACTIONS = Set.of(ItemAbility.get("till"));

    public static final Set<ItemAbility> DEFAULT_SHOVEL_ACTIONS = Set.of(ItemAbility.get("shovel_flatten"), ItemAbility.get("shovel_douse"));

    public static final Set<ItemAbility> DEFAULT_SHEARS_ACTIONS = Set.of(ItemAbility.get("shears_dig"), ItemAbility.get("shears_harvest"), ItemAbility.get("shears_remove_armor"), ItemAbility.get("shears_carve"), ItemAbility.get("shears_disarm"), ItemAbility.get("shears_trim"));

    public ItemAbilities() {
    }

    // Pumpkin divergence: no throwing initializer -- names from NeoForge's own table.
}
