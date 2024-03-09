package com.github.brainage04.fuckwaterboard.config.categories;

import com.google.gson.annotations.Expose;
import io.github.moulberry.moulconfig.annotations.ConfigEditorBoolean;
import io.github.moulberry.moulconfig.annotations.ConfigOption;

public class TooltipsCategory {

    @Expose
    @ConfigOption(name = "Launch Tooltip", desc = "Gives users a heads up about how to use the mod after joining a world for the first time during a play session.")
    @ConfigEditorBoolean
    public boolean launchTooltip = true;
}
