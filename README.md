# YellowLLMDesktopClient

[Download builds](/download)

## Build yourself

In case you Java version is different than specified in `yllmc/settings.gradle.kts`:

Seek the structure
```
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
```

and specify your version of java.

Then run `./gradlew build` (linux or macOS); your build will be put to `yllmc/build/distributions/` directory.


If building yourself, you might also find it interesting to look into `yllmc/src/main/java/utils/Config.java`

## General
 Keyboard shortcut *Ctrl+Enter* sends the message.

## OpenAI
 Run the app, set up the API key: *Menu -> LLM Clients Properties -> OpenAI*
 You are good to go.