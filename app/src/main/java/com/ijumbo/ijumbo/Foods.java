package com.ijumbo.ijumbo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Foods {

    @SerializedName("meal")
    List<Meal> mMeals;

    public static class Meal {
        @SerializedName("meal-name")
        String mName;
        @SerializedName("menu")
        List<Menu> mMenus;
    }

    public static class Menu {
        @SerializedName("menu-section")
        MenuSection mSection;
    }

    public static class MenuSection {
        @SerializedName("category")
        String mCategory;
        @SerializedName("items")
        String mItems;
    }

}
