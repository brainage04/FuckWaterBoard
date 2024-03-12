package com.github.brainage04.fuckwaterboard.config.categories;

import com.google.gson.annotations.Expose;
import io.github.moulberry.moulconfig.annotations.Accordion;
import io.github.moulberry.moulconfig.annotations.ConfigEditorBoolean;
import io.github.moulberry.moulconfig.annotations.ConfigEditorSlider;
import io.github.moulberry.moulconfig.annotations.ConfigOption;

public class MainCategory {
    public static class SoundToggle {
        @Expose
        @ConfigOption(name = "Enabled", desc = "Allows this sound to be triggered (via text). Disabling this disables all other triggers.")
        @ConfigEditorBoolean
        public boolean enabled;

        @Expose
        @ConfigOption(name = "Enabled (Area-based)", desc = "Allows this sound to be triggered via entering a certain area.")
        @ConfigEditorBoolean
        public boolean areaEnabled;

        SoundToggle(boolean enabled, boolean areaEnabled) {
            this.enabled = enabled;
            this.areaEnabled = areaEnabled;
        }
    }

    public static class SoundToggles {
        @Expose
        @ConfigOption(name = "FUCK WATERBOARD", desc = "Settings for the \"FUCK WATERBOARD\" sound.")
        @Accordion
        public SoundToggle fuckWaterBoard;

        SoundToggles(SoundToggle fuckWaterBoard) {
            this.fuckWaterBoard = fuckWaterBoard;
        }
    }

    public static class AmbientSound {
        @Expose
        @ConfigOption(name = "Toggle Sound", desc = "Toggles this ambient sound.")
        @ConfigEditorBoolean
        public boolean enabled;

        @Expose
        @ConfigOption(name = "Minimum Delay", desc = "Minimum delay between sounds.")
        @ConfigEditorSlider(minValue = 20, maxValue = 6000, minStep = 1)
        public int minDelay;

        @Expose
        @ConfigOption(name = "Maximum Delay", desc = "Maximum delay between sounds.")
        @ConfigEditorSlider(minValue = 20, maxValue = 6000, minStep = 1)
        public int maxDelay;

        AmbientSound(boolean enabled, int minDelay, int maxDelay) {
            this.enabled = enabled;
            this.minDelay = minDelay;
            this.maxDelay = maxDelay;
        }
    }

    public static class AmbientSounds {
        @Expose
        @ConfigOption(name = "Villager Sounds", desc = "Settings for ambient Villager sounds.")
        @Accordion
        public AmbientSound ambientSound;

        AmbientSounds(AmbientSound ambientSound) {
            this.ambientSound = ambientSound;
        }
    }

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

    public static class DangerZone {
        @Expose
        @ConfigOption(name = "Waterboard Cooldown", desc = "Sets the minimum cooldown period between chat/sound triggers.\nWarning: low values may result in VERY LOUD SOUNDS, being kicked from the server for spanning and cause undocumented behaviour.")
        @ConfigEditorSlider(minValue = 20, maxValue = 100, minStep = 1)
        public int waterboardCooldown;

        DangerZone(int waterboardCooldown) {
            this.waterboardCooldown = waterboardCooldown;
        }
    }

    @Expose
    @ConfigOption(name = "Sound Triggers", desc = "")
    @Accordion
    public SoundToggles soundTriggers = new SoundToggles(new SoundToggle(true, true));

    @Expose
    @ConfigOption(name = "Ambient Sounds", desc = "")
    @Accordion
    public AmbientSounds ambientSounds = new AmbientSounds(new AmbientSound(true, 60, 100));

    @Expose
    @ConfigOption(name = "Random Ranges", desc = "")
    @Accordion
    public RandomRanges randomRanges = new RandomRanges(3, 15);

    @Expose
    @ConfigOption(name = "DANGER ZONE", desc = "")
    @Accordion
    public DangerZone dangerZone = new DangerZone(40);
}
