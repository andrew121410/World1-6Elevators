package com.andrew121410.mc.world16elevators.objects;


import com.andrew121410.mc.world16elevators.Main;
import com.andrew121410.mc.world16utils.blocks.sign.SignUtils;
import com.andrew121410.mc.world16utils.blocks.sign.SignUtils_V1_16_R1;
import com.andrew121410.mc.world16utils.chat.Translate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode
@ToString
@Getter
@SerializableAs("SignObject")
public class SignObject implements ConfigurationSerializable {

    private Location location;
    private SignUtils signUtils;

    public SignObject(Location location) {
        this.location = location;
        this.signUtils = new SignUtils_V1_16_R1(Main.getInstance());
    }

    public void doUpArrow() {
        Sign sign = signUtils.isSign(location.getBlock());
        if (sign == null) return;
        String text = signUtils.centerText("/\\", 16);
        String text1 = signUtils.centerText("//\\\\", 16);
        sign.setLine(0, Translate.chat("&a&l" + text));
        sign.setLine(1, Translate.chat("&a&l" + text1));
        sign.update();
    }

    public void doDownArrow() {
        Sign sign = signUtils.isSign(location.getBlock());
        if (sign == null) return;
        String text = signUtils.centerText("\\\\//", 16);
        String text1 = signUtils.centerText("\\/", 16);
        sign.setLine(2, Translate.chat("&c&l" + text));
        sign.setLine(3, Translate.chat("&c&l" + text1));
        sign.update();
    }

    public void clearSign() {
        Sign sign = signUtils.isSign(location.getBlock());
        if (sign == null) return;
        sign.setLine(0, "");
        sign.setLine(1, "");
        sign.setLine(2, "");
        sign.setLine(3, "");
        sign.update();
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("Location", location);
        return map;
    }

    public static SignObject deserialize(Map<String, Object> map) {
        return new SignObject((Location) map.get("Location"));
    }
}