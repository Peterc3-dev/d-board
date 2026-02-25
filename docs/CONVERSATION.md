# CONVERSATION.md — D-Board

The build dialogue behind this keyboard, captured from conversations between the developer (Boo) and Claude (Opus).

---

## The Spark

The idea didn't come from a keyboard discussion. It came from a conversation about cognitive processing speed and how standard systems aren't built for non-standard architectures. The parallel was immediate: "Society doesn't provide keyboards that match how I think" maps directly to "Society doesn't provide structures that match how I process."

The response to both: build it yourself.

A search of the Play Store confirmed there was no ortholinear keyboard app available. Mechanical ortholinear keyboards exist (Planck, Preonic, etc.) but nobody had brought the concept to mobile.

The grid layout — where fingers move in straight lines instead of diagonal paths — isn't just an aesthetic preference. It's ergonomically correct for a flat touchscreen surface.

---

## Pre-Planning: Core Decisions

Before writing any code, three fundamental decisions shaped everything:

**1. Layout Grid**

Standard ortholinear is 4x12, but mobile has different constraints. Touch targets need to be larger than physical keycaps. The final design uses a 10-key-wide grid (matching QWERTY row length) with 4 functional rows plus an arrow row at top.

**2. IME vs. Standalone App**

Three options were considered:

- **Custom IME (InputMethodService)** — system-wide, works in every app, more complex
- **React Native / Flutter** — cross-platform, faster prototyping, limited IME integration
- **Pure Android native** — maximum control, steeper learning curve

Decision: Custom IME via `InputMethodService`. The keyboard needs to work everywhere — in messaging apps, browsers, search bars. A standalone app with an embedded keyboard would be a toy. A real IME is a tool.

**3. Kotlin vs. Java**

Kotlin. Modern Android standard, less boilerplate, better null safety. No reason to use Java for a new project in 2026.

---

## Build Session: The Grid Takes Shape

### The Layout (keyboard.xml)

The grid was built with nested `LinearLayout` rows using equal `layout_weight` on every button. This guarantees perfect grid alignment — no key is wider or narrower than its neighbors (except intentional exceptions like Enter and Space).

```xml
<Button
    android:id="@+id/key_q"
    android:layout_width="0dp"
    android:layout_height="60dp"
    android:layout_weight="1"
    android:text="Q" />
```

Every key: `0dp` width, `weight="1"`, `60dp` height. The system distributes width equally. True grid.

**Row structure:**
- Row 0: Left arrow, Right arrow (cursor navigation)
- Row 1: Q W E R T Y U I O P (10 keys)
- Row 2: A S D F G H J K L . (10 keys)
- Row 3: Z X C V [Enter] B N M ? (9 keys, Enter gets weight=2)
- Row 4: Delete, Space-L, ↑↓, Space-R, Backspace (split design)

### The Service (OrthoKeyboardService.kt)

The IME service is minimal by design — inflate the layout, wire up click listeners, commit text:

```kotlin
class OrthoKeyboardService : InputMethodService() {
    override fun onCreateInputView(): View {
        val view = layoutInflater.inflate(R.layout.keyboard, null)
        view.findViewById<Button>(R.id.key_q).setOnClickListener { commitText("q") }
        // ... all keys wired
        return view
    }

    private fun commitText(text: String) {
        currentInputConnection?.commitText(text, 1)
    }
}
```

No abstraction layers, no key mapping tables, no over-engineering. Each button directly calls `commitText()` with its character. Backspace uses `deleteSurroundingText(1, 0)`. Arrow keys use `sendKeyEvent()` with DPAD keycodes.

### The Split Spacebar

The bottom row has two space buttons — left thumb and right thumb zones. Between them sits a vertically stacked up/down arrow pair. This keeps cursor navigation accessible without reaching.

### The Setup Activity (MainActivity.kt)

Single button that launches `Settings.ACTION_INPUT_METHOD_SETTINGS` so the user can enable the keyboard. Nothing else. The app's only purpose outside the keyboard itself is this one-time setup step.

---

## Testing

Tested by installing the APK directly on an Android device, enabling the IME in system settings, and typing in various apps. Core typing works — lowercase letters, period, question mark, enter, backspace, delete, and all four arrow directions.

The missing features (capitalization, numbers, special characters) became apparent immediately during real use. The prototype proves the grid concept works on mobile — the next iteration addresses the feature gaps.

---

## Timeline

- **Concept → architecture decision:** Same conversation
- **Architecture → working APK:** Same session
- **Status:** v1.0 prototype, functional but feature-incomplete

---

*This document captures the build process. For design philosophy and lessons learned, see [EVOLUTION.md](EVOLUTION.md). For installation and feature details, see [README.md](../README.md).*
