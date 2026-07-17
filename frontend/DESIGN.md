---
version: "alpha"
name: Issura
description: A sober, modern, institutional, welcoming, and technical design system for professional ticket management.
colors:
  primary: "#1D4ED8"
  on-primary: "#FFFFFF"
  primary-hover: "#1E40AF"
  secondary: "#475569"
  on-secondary: "#FFFFFF"
  accent: "#0F766E"
  on-accent: "#FFFFFF"
  background: "#F8FAFC"
  surface: "#FFFFFF"
  text: "#0F172A"
  muted: "#475569"
  border: "#CBD5E1"
  info: "#0369A1"
  on-info: "#FFFFFF"
  success: "#15803D"
  on-success: "#FFFFFF"
  warning: "#B45309"
  on-warning: "#FFFFFF"
  danger: "#B91C1C"
  on-danger: "#FFFFFF"
typography:
  display:
    fontFamily: Inter
    fontSize: 2rem
    fontWeight: 700
    lineHeight: 2.5rem
    letterSpacing: -0.02em
  heading-lg:
    fontFamily: Inter
    fontSize: 1.5rem
    fontWeight: 700
    lineHeight: 2rem
  heading-md:
    fontFamily: Inter
    fontSize: 1.125rem
    fontWeight: 600
    lineHeight: 1.75rem
  body:
    fontFamily: Inter
    fontSize: 1rem
    fontWeight: 400
    lineHeight: 1.5rem
  body-sm:
    fontFamily: Inter
    fontSize: 0.875rem
    fontWeight: 400
    lineHeight: 1.25rem
  label:
    fontFamily: Inter
    fontSize: 0.875rem
    fontWeight: 600
    lineHeight: 1.25rem
  code:
    fontFamily: JetBrains Mono
    fontSize: 0.875rem
    fontWeight: 400
    lineHeight: 1.25rem
rounded:
  sm: 4px
  md: 8px
  lg: 12px
  pill: 999px
spacing:
  xs: 4px
  sm: 8px
  md: 16px
  lg: 24px
  xl: 32px
  2xl: 48px
components:
  page:
    backgroundColor: "{colors.background}"
    textColor: "{colors.text}"
    typography: "{typography.body}"
  surface:
    backgroundColor: "{colors.surface}"
    textColor: "{colors.text}"
    rounded: "{rounded.md}"
    padding: "{spacing.md}"
  primary-button:
    backgroundColor: "{colors.primary}"
    textColor: "{colors.on-primary}"
    typography: "{typography.label}"
    rounded: "{rounded.md}"
    padding: "{spacing.sm}"
  primary-button-hover:
    backgroundColor: "{colors.primary-hover}"
    textColor: "{colors.on-primary}"
  secondary-button:
    backgroundColor: "{colors.secondary}"
    textColor: "{colors.on-secondary}"
    typography: "{typography.label}"
    rounded: "{rounded.md}"
    padding: "{spacing.sm}"
  accent-action:
    backgroundColor: "{colors.accent}"
    textColor: "{colors.on-accent}"
    typography: "{typography.label}"
    rounded: "{rounded.md}"
    padding: "{spacing.sm}"
  muted-content:
    backgroundColor: "{colors.surface}"
    textColor: "{colors.muted}"
    typography: "{typography.body-sm}"
  divider:
    backgroundColor: "{colors.border}"
    textColor: "{colors.text}"
    height: 1px
  status-new:
    backgroundColor: "{colors.info}"
    textColor: "{colors.on-info}"
    typography: "{typography.label}"
    rounded: "{rounded.pill}"
    padding: "{spacing.xs}"
  status-in-progress:
    backgroundColor: "{colors.warning}"
    textColor: "{colors.on-warning}"
    typography: "{typography.label}"
    rounded: "{rounded.pill}"
    padding: "{spacing.xs}"
  status-resolved:
    backgroundColor: "{colors.success}"
    textColor: "{colors.on-success}"
    typography: "{typography.label}"
    rounded: "{rounded.pill}"
    padding: "{spacing.xs}"
  status-blocked:
    backgroundColor: "{colors.danger}"
    textColor: "{colors.on-danger}"
    typography: "{typography.label}"
    rounded: "{rounded.pill}"
    padding: "{spacing.xs}"
---

## Overview

Issura should feel reliable before it feels expressive. The interface combines institutional clarity with a calm technical character suitable for teams that spend long periods triaging, assigning, and resolving tickets.

Use strong hierarchy, restrained color, generous whitespace, and concise labels. Warmth comes from clear language, comfortable spacing, and teal accents—not decoration. Dense operational screens may be compact, but must never feel crowded or ambiguous.

## Colors

Blue is the primary action color because it communicates trust, focus, and institutional stability. Slate neutrals carry most interface structure. Teal is a selective accent for constructive actions and supportive emphasis.

Semantic colors are reserved for ticket state and system feedback:

- **Info:** new or informational state.
- **Warning:** pending attention or work in progress.
- **Success:** resolved or completed state.
- **Danger:** blocked state, destructive action, or critical failure.

Never rely on color alone. Every state must also use a text label, icon, or both. Use the light background for application canvas and white surfaces for cards, tables, dialogs, and side panels.

## Typography

Inter is the default interface family for its clarity at small sizes and neutral professional tone. Use JetBrains Mono only for ticket identifiers, logs, stack traces, commands, and other machine-oriented content.

Display typography is limited to major page titles and empty-state headings. Operational screens should favor medium headings, body text, and compact labels. Avoid uppercase paragraphs; uppercase may appear only in short metadata labels when letter spacing remains readable.

## Layout

Use an 8px-centered spacing rhythm, with 4px reserved for tight internal relationships. Prefer clear page regions: navigation, context header, primary work area, and optional detail panel.

Ticket lists prioritize scanning. Keep identifier, title, status, priority, assignee, and updated time aligned consistently. Ticket detail pages should place core conversation and activity in the main column, with metadata and actions in a narrower secondary column.

Responsive layouts collapse secondary panels below main content before hiding information. Interactive controls require at least a 44px touch target even when visual padding appears compact.

## Elevation & Depth

Use borders and background contrast before shadows. Cards and tables should normally sit on white surfaces with subtle slate borders. Reserve shadows for transient layers such as menus, popovers, dialogs, and drag previews.

Avoid stacking multiple elevated surfaces. Depth must communicate interaction hierarchy, not decoration.

## Shapes

Use 8px rounding for controls and standard surfaces, 12px for large dialogs or prominent panels, and pill rounding only for status badges and compact filters. Technical content areas may use 4px rounding.

Do not mix sharp and highly rounded components in the same workflow. Ticket management should feel precise, not playful.

## Components

Primary buttons represent the main action in the current context. Limit each visible region to one primary action. Secondary buttons handle navigation and reversible supporting actions. Teal accent actions are reserved for constructive workflow transitions that deserve emphasis without competing with the primary action.

Status badges always pair semantic color with explicit text. Tables need clear row hover and keyboard focus states, while preserving readable contrast. Forms place labels above controls, keep validation near the affected field, and distinguish required fields without depending only on color.

Ticket identifiers use monospace typography and remain visually secondary to ticket titles. Activity timelines distinguish human comments, automated events, and internal notes through labels and structure rather than excessive color.

## Do's and Don'ts

**Do:**

- Keep workflows direct, predictable, and keyboard accessible.
- Preserve visible focus indicators on every interactive element.
- Use plain, actionable language for errors and empty states.
- Combine status color with labels and icons.
- Keep dense data aligned and easy to scan.
- Meet WCAG AA contrast for text and controls.

**Don't:**

- Use gradients, glass effects, or decorative motion in operational screens.
- Turn every action into a blue button.
- Use red for non-critical emphasis.
- Hide essential ticket metadata behind hover-only interactions.
- Use color as the only carrier of meaning.
- Sacrifice readability to maximize information density.
