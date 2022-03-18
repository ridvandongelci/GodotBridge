package com.appsan.godotbridge;

import androidx.annotation.NonNull;

import com.appsan.godotbridge.events.AppMessage;
import com.appsan.godotbridge.events.GodotMessage;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashSet;
import java.util.Set;

public class GodotBridge extends GodotPlugin {
    private static final String SIGNAL_NAME = "on_godot_message";

    public GodotBridge(Godot godot) {
        super(godot);
    }

    @UsedByGodot
    public void sendAppMessage(String string) {
        EventBus.getDefault().post(new AppMessage(string));
    }

    @Override
    public void onMainPause() {
        super.onMainPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onMainResume() {
        super.onMainResume();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onGodotMessage(GodotMessage godotMessage) {
        emitSignal(SIGNAL_NAME, godotMessage.getMessage());
    }

    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals() {
        Set<SignalInfo> signals = new HashSet<>();
        signals.add(new SignalInfo(SIGNAL_NAME, String.class));
        return signals;
    }

    @NonNull
    @Override
    public String getPluginName() {
        return getActivity().getString(R.string.library_name);
    }
}
