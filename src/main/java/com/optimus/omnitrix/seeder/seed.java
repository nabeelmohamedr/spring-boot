package com.optimus.omnitrix.seeder;

import com.optimus.omnitrix.entity.asmith_entity;
import com.optimus.omnitrix.repo.asmith_repositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  // ✅ Make Spring pick it up as a bean
public class seed implements CommandLineRunner {

    @Autowired
    private asmith_repositary asmith_repositary;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (asmith_repositary.count() == 0) {
            List<asmith_entity> demoData = List.of(
                    new asmith_entity(0, 900, "Ben Tennyson", "ben10", "ben10@omniverse.com", passwordEncoder.encode("hero123")),
                    new asmith_entity(0, 700, "Gwen Tennyson", "gwen", "gwen@omniverse.com", passwordEncoder.encode("magic123")),
                    new asmith_entity(0, 800, "Kevin Levin", "kevin", "kevin@omniverse.com", passwordEncoder.encode("metal123")),
                    new asmith_entity(0, 1000, "Vilgax", "vilgax", "vilgax@alienforce.com", passwordEncoder.encode("destroyer")),
                    new asmith_entity(0, 950, "Azmuth", "azmuth", "azmuth@galvan.com", passwordEncoder.encode("creator123")),

                    new asmith_entity(0, 100, "Optimus Prime", "optimus", "optimus@cybertron.com", passwordEncoder.encode("prime123")),
                    new asmith_entity(0, 250, "Bumblebee", "bee", "bumblebee@cybertron.com", passwordEncoder.encode("bee123")),
                    new asmith_entity(0, 400, "Ironhide", "iron", "ironhide@cybertron.com", passwordEncoder.encode("iron123")),
                    new asmith_entity(0, 550, "Ratchet", "ratchet", "ratchet@cybertron.com", passwordEncoder.encode("medic123")),
                    new asmith_entity(0, 700, "Megatron", "mega", "megatron@decepticon.com", passwordEncoder.encode("mega123")),
                    new asmith_entity(0, 850, "Starscream", "scream", "starscream@decepticon.com", passwordEncoder.encode("scream123")),
                    new asmith_entity(0, 950, "Soundwave", "sound", "soundwave@decepticon.com", passwordEncoder.encode("sound123")),
                    new asmith_entity(0, 150, "Wheeljack", "wheel", "wheeljack@cybertron.com", passwordEncoder.encode("wheel123")),
                    new asmith_entity(0, 275, "Arcee", "arcee", "arcee@cybertron.com", passwordEncoder.encode("arcee123")),
                    new asmith_entity(0, 325, "Cliffjumper", "cliff", "cliffjumper@cybertron.com", passwordEncoder.encode("cliff123")),
                    new asmith_entity(0, 375, "Jazz", "jazz", "jazz@cybertron.com", passwordEncoder.encode("jazz123")),
                    new asmith_entity(0, 425, "Prowl", "prowl", "prowl@cybertron.com", passwordEncoder.encode("prowl123")),
                    new asmith_entity(0, 475, "Hound", "hound", "hound@cybertron.com", passwordEncoder.encode("hound123")),
                    new asmith_entity(0, 525, "Kup", "kup", "kup@cybertron.com", passwordEncoder.encode("kup123")),
                    new asmith_entity(0, 575, "Blurr", "blurr", "blurr@cybertron.com", passwordEncoder.encode("blurr123")),
                    new asmith_entity(0, 625, "Hot Rod", "hotrod", "hotrod@cybertron.com", passwordEncoder.encode("hotrod123")),
                    new asmith_entity(0, 675, "Springer", "springer", "springer@cybertron.com", passwordEncoder.encode("spring123")),
                    new asmith_entity(0, 725, "Ultra Magnus", "ultra", "ultramagnus@cybertron.com", passwordEncoder.encode("ultra123")),
                    new asmith_entity(0, 775, "Blaster", "blaster", "blaster@cybertron.com", passwordEncoder.encode("blast123")),
                    new asmith_entity(0, 825, "Perceptor", "perceptor", "perceptor@cybertron.com", passwordEncoder.encode("percep123")),
                    new asmith_entity(0, 875, "Grimlock", "grim", "grimlock@dinobot.com", passwordEncoder.encode("grim123")),
                    new asmith_entity(0, 925, "Slag", "slag", "slag@dinobot.com", passwordEncoder.encode("slag123")),
                    new asmith_entity(0, 975, "Sludge", "sludge", "sludge@dinobot.com", passwordEncoder.encode("sludge123")),
                    new asmith_entity(0, 150, "Snarl", "snarl", "snarl@dinobot.com", passwordEncoder.encode("snarl123")),
                    new asmith_entity(0, 200, "Swoop", "swoop", "swoop@dinobot.com", passwordEncoder.encode("swoop123")),
                    new asmith_entity(0, 300, "Shockwave", "shock", "shockwave@decepticon.com", passwordEncoder.encode("shock123")),
                    new asmith_entity(0, 450, "Skywarp", "skywarp", "skywarp@decepticon.com", passwordEncoder.encode("warp123")),
                    new asmith_entity(0, 600, "Thundercracker", "thunder", "thundercracker@decepticon.com", passwordEncoder.encode("thunder123")),
                    new asmith_entity(0, 750, "Galvatron", "galva", "galvatron@decepticon.com", passwordEncoder.encode("galva123")),
                    new asmith_entity(0, 900, "Unicron", "unicron", "unicron@chaos.com", passwordEncoder.encode("uni123"))

            );

            asmith_repositary.saveAll(demoData); // ✅ Save into DB
            System.out.println("🌱 Seeded demo users into database!");
        }
        else {
            System.out.println("🌱Users table already has data. Skipping seeding.");
        }
    }
}
