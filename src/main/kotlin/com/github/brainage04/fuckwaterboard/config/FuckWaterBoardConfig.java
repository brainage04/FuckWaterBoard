package com.github.brainage04.fuckwaterboard.config;

import com.github.brainage04.fuckwaterboard.FuckWaterBoard;
import com.github.brainage04.fuckwaterboard.config.categories.MainCategory;
import com.github.brainage04.fuckwaterboard.config.categories.TooltipsCategory;
import com.google.gson.annotations.Expose;
import io.github.moulberry.moulconfig.Config;
import io.github.moulberry.moulconfig.annotations.Category;

public class FuckWaterBoardConfig extends Config {

    @Override
    public String getTitle() {
        return FuckWaterBoard.MOD_NAME + " " + FuckWaterBoard.getVersion() + " by §cbrainage04§r, config by §5Moulberry §rand §5nea89";
    }

    @Override
    public void saveNow() {
        FuckWaterBoard.configManager.save();
    }

    @Expose
    @Category(name = "Main Category", desc = "This is the main category.")
    public MainCategory mainCategory = new MainCategory();

    @Expose
    @Category(name = "Tooltips Category", desc = "This is the tooltips category.")
    public TooltipsCategory tooltipsCategory = new TooltipsCategory();
}
