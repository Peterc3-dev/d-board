# D-Board

An ortholinear Android keyboard — true grid layout with no key stagger.

## Problem

Every mobile keyboard on the Play Store uses staggered key rows inherited from mechanical typewriters. That stagger exists because physical typewriter arms needed clearance to avoid jamming. On a touchscreen, there's no mechanical reason for it — it's a vestigial design pattern that forces fingers to move in diagonal paths instead of straight lines.

D-Board removes the stagger. Every key sits on a perfect grid. Fingers move vertically and horizontally, never diagonally.

## Features (v1.0 — Prototype)

- **True ortholinear grid** — 10 keys per row, zero stagger
- **System-wide IME** — works in any app via Android's InputMethodService
- **Split spacebar** — left and right thumb zones
- **Embedded arrow cluster** — cursor navigation without leaving the keyboard
- **Dark theme** — `#2C2C2C` background, `#1E1E1E` keys, white text
- **Directional arrows** — left/right at top, up/down embedded in bottom row

## Current Status: Working Prototype

The APK installs and functions as a system-wide keyboard replacement. Lowercase typing, backspace, delete, enter, period, question mark, and full cursor navigation all work.

**Not yet implemented:**
- Capitalization / shift key
- Number row or number layer
- Special characters beyond `.` and `?`
- Long-press behaviors
- Haptic feedback
- Autocorrect / prediction

## Architecture

```
AndroidManifest.xml
├── MainActivity          (setup screen — launches IME settings)
└── OrthoKeyboardService  (InputMethodService — the actual keyboard)
    ├── keyboard.xml      (grid layout definition)
    ├── commitText()      (sends characters to active input field)
    ├── deleteText()      (backspace via deleteSurroundingText)
    └── sendKey()         (arrow key events via sendKeyEvent)
```

Built as a native Android IME in Kotlin. No frameworks, no dependencies beyond AndroidX. The keyboard layout is pure XML with `LinearLayout` rows and equal-weight buttons for perfect grid alignment.

## Installation

1. Build from source in Android Studio, or install `d-board-v1_0.apk` directly
2. Go to Settings → System → Languages & Input → On-screen keyboard
3. Enable "OrthoKeyboard"
4. Switch to it via your keyboard selector

## Tech Stack

`Kotlin` · `Android InputMethodService` · `XML Layouts` · `Android SDK 36` · `Min SDK 24`

## Roadmap

- [ ] Shift/caps layer
- [ ] Number layer (hold or toggle)
- [ ] Special character layer
- [ ] Long-press for alternate characters
- [ ] Haptic feedback on keypress
- [ ] Custom theming (Phosphor green variant)
- [ ] Key size optimization based on finger heat maps

---

*For the build story and design decisions, see [docs/CONVERSATION.md](docs/CONVERSATION.md). For design philosophy and lessons learned, see [docs/EVOLUTION.md](docs/EVOLUTION.md).*
