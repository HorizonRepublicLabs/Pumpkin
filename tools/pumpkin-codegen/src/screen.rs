use std::fs;

use proc_macro2::TokenStream;
use quote::quote;

use crate::array_to_tokenstream;

/// Generates the `TokenStream` for the `WindowType` enum.
pub fn build() -> TokenStream {
    let screens: Vec<String> =
        serde_json::from_str(&fs::read_to_string("../../assets/screens.json").unwrap())
            .expect("Failed to parse screens.json");
    let variants = array_to_tokenstream(&screens);
    let names = screens.iter().map(|name| quote! { #name, });

    quote! {
        #[derive(Debug, Clone, Copy, PartialEq, Eq, Hash)]
        pub enum WindowType {
            #variants
        }

        #[doc = r" Every generated menu type, indexed by its registry id."]
        #[doc = r""]
        #[doc = r" Runtime-registered menu types continue from the end of this list; use"]
        #[doc = r" `pumpkin_data::dynamic::menu_type_name` to resolve either kind."]
        pub const MENU_TYPES: &[&str] = &[
            #(#names)*
        ];
    }
}
