# EVOLUTION.md — D-Board

The design philosophy, pivots, and deeper lessons from building a keyboard that questions a 150-year-old assumption.

---

## Origin: Typewriter Ghosts in Your Phone

Every keyboard you've ever used on a phone inherited its layout from a machine designed in the 1870s. The staggered rows on a QWERTY keyboard exist because mechanical typewriter arms needed physical clearance to avoid jamming. Each row is offset from the one above it so the metal levers could sit side by side.

On a touchscreen, there are no levers. There is no mechanical reason for the stagger. But every major mobile keyboard — Gboard, SwiftKey, Samsung Keyboard — faithfully reproduces it.

D-Board asks: what if we didn't?

---

## The Grid Argument

On a flat surface, the most efficient movement is straight up and straight down. Staggered rows force diagonal finger travel — your index finger reaching for "R" from "F" moves up-and-left instead of straight up. Over thousands of keypresses per day, that diagonal adds up to unnecessary movement and cognitive overhead.

Ortholinear layout eliminates this. Every key sits directly above and below its neighbors. Finger paths are vertical. The mental model simplifies: the keyboard becomes a coordinate grid where position is predictable.

This matters more on mobile than on desktop. On a physical keyboard, your fingers rest on home row and reach from a fixed position. On a touchscreen, you're visually targeting every key. A predictable grid means faster visual acquisition — you know where the key is because the pattern is geometrically regular.

---

## Design Decisions That Shaped the Prototype

### Split Spacebar

Most mobile keyboards have one wide spacebar. D-Board splits it into two thumb zones — left and right. This isn't arbitrary: on a phone held with two hands, each thumb naturally rests over a different horizontal region. Two smaller space bars mean neither thumb has to reach as far. Between the split spaces sits the up/down arrow cluster. Cursor navigation lives where dead space usually is.

### Enter Key Placement

Enter sits in the middle of Row 3 (between V and B) with double width. This is unconventional — most keyboards put Enter on the far right. But the center position means either thumb can reach it, and its double width makes it a distinct tactile target even without haptic feedback.

### Arrow Keys as First-Class Citizens

Most mobile keyboards bury cursor navigation behind long-press or secondary menus. D-Board puts arrows on the main layout — left/right across the top row, up/down embedded in the bottom row. Text editing shouldn't require leaving the keyboard or switching modes.

### Dark Theme Default

`#2C2C2C` background, `#1E1E1E` keys. Not configurable in v1.0, but dark by default because that's the environment it was built for — extended use sessions where screen brightness matters.

---

## What the Prototype Proved

**The grid works on mobile.** The concern was that equal-width keys on a phone screen would be too small to hit accurately. In practice, the 10-key row at 60dp height is usable. Not perfect — some keys near the edges are harder to reach — but the core concept is validated.

**The split spacebar works.** Both thumbs can hit space without crossing the midline. The embedded arrow cluster between them feels natural.

**What doesn't work yet:** The absence of capitalization, numbers, and special characters makes it impractical as a daily driver. The prototype proves the layout; the next version needs to prove the layer system.

---

## The Layer Problem (Next Iteration)

The biggest architectural question for v2.0: how to add shift, numbers, and symbols without abandoning the grid philosophy.

Options being considered:

1. **Hold-to-shift** — hold a modifier key, tap a letter, get uppercase. Simple but occupies a finger.
2. **Layer toggle** — dedicated key swaps the entire keyboard between alpha/number/symbol layers. Clean but requires mode awareness.
3. **Long-press** — hold any key for its alternate character (number or symbol). No mode switching but harder to discover.
4. **Swipe** — swipe up on a key for its shifted/alternate version. Preserves the grid, adds a gesture layer.

The decision will come from testing, not theory. Whichever approach feels fastest in actual use wins.

---

## What This Project Says

This is the third tool built from the same philosophy: identify where a legacy design pattern creates friction, then build the alternative rather than adapting to the constraint.

- **Claude Wide Chat** — removed artificial width constraints on a chat interface
- **Phosphor Green Stylus** — replaced white backgrounds with hardware-compensated color science
- **D-Board** — replaced 150-year-old key stagger with a grid that matches how touchscreens actually work

The pattern: don't complain about tools that don't fit. Build the one that does.

---

## Connected Projects

- **[Claude Wide Chat](https://github.com/Peterc3-dev/claude-wide-chat)** — Same philosophy: remove legacy UI constraints
- **[Phosphor Green Stylus](https://github.com/Peterc3-dev/phosphor-green-stylus)** — Same philosophy: design for actual hardware, not assumptions
- **[Learning Forge](https://github.com/Peterc3-dev/learning-forge)** — Android development patterns documented in learning hub

---

*This document captures the evolution of thinking. For the build dialogue, see [CONVERSATION.md](CONVERSATION.md). For installation and features, see [README.md](../README.md).*
