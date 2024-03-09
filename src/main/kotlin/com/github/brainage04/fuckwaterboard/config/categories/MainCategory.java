package com.github.brainage04.fuckwaterboard.config.categories;

import com.google.gson.annotations.Expose;
import io.github.moulberry.moulconfig.annotations.Accordion;
import io.github.moulberry.moulconfig.annotations.ConfigEditorBoolean;
import io.github.moulberry.moulconfig.annotations.ConfigEditorSlider;
import io.github.moulberry.moulconfig.annotations.ConfigOption;

public class MainCategory {

    @Expose
    @ConfigOption(name = "Sound Toggles", desc = "")
    @Accordion
    public SoundToggles soundToggles = new SoundToggles(true);

    public static class SoundToggles {
        @Expose
        @ConfigOption(name = "Toggle \"FUCK WATERBOARD\"", desc = "Toggles the \"FUCK WATERBOARD\" sound")
        @ConfigEditorBoolean
        public boolean fuckWaterBoard;

        SoundToggles(boolean fuckWaterBoard) {
            this.fuckWaterBoard = fuckWaterBoard;
        }
    }

    @Expose
    @ConfigOption(name = "Random Ranges", desc = "")
    @Accordion
    public RandomRanges randomRanges = new RandomRanges(3, 15);

    public static class RandomRanges {
        @Expose
        @ConfigOption(name = "Exclamation Points (Minimum)", desc = "Minimum number of exclamation points appended to the end of automated messages.")
        @ConfigEditorSlider(minValue = 0, maxValue = 100, minStep = 1)
        public int minExclamPoints;

        @Expose
        @ConfigOption(name = "Exclamation Points (Maximum)", desc = "Maximum number of exclamation points appended to the end of automated messages.")
        @ConfigEditorSlider(minValue = 0, maxValue = 100, minStep = 1)
        public int maxExclamPoints;

        RandomRanges(int minExclamPoints, int maxExclamPoints) {
            this.minExclamPoints = minExclamPoints;
            this.maxExclamPoints = maxExclamPoints;
        }
    }

    @Expose
    @ConfigOption(name = "DANGER ZONE", desc = "")
    @Accordion
    public DangerZone dangerZone = new DangerZone(40);

    public static class DangerZone {
        @Expose
        @ConfigOption(name = "Waterboard Cooldown", desc = "Sets the minimum cooldown period between chat/sound triggers.\nWarning: low values may result in VERY LOUD SOUNDS, being kicked from the server for spanning and cause undocumented behaviour.")
        @ConfigEditorSlider(minValue = 20, maxValue = 100, minStep = 1)
        public int waterboardCooldown;

        DangerZone(int waterboardCooldown) {
            this.waterboardCooldown = waterboardCooldown;
        }
    }
}
