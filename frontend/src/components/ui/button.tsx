import * as React from "react";
import { cva, type VariantProps } from "class-variance-authority";
import { Slot } from "radix-ui";

import { cn } from "@/lib/utils";

const buttonVariants = cva(
	"group/button inline-flex min-h-11 shrink-0 items-center justify-center rounded-md border border-transparent bg-clip-padding text-sm font-medium whitespace-nowrap transition-colors outline-none select-none focus-visible:border-ring focus-visible:ring-3 focus-visible:ring-ring/50 disabled:pointer-events-none disabled:opacity-50 aria-invalid:border-destructive aria-invalid:ring-3 aria-invalid:ring-destructive/20 [&_svg]:pointer-events-none [&_svg]:shrink-0 [&_svg:not([class*='size-'])]:size-4",
	{
		variants: {
			variant: {
				default: "bg-primary text-primary-foreground hover:bg-primary-hover",
				outline:
					"border-border bg-surface text-foreground hover:bg-background aria-expanded:bg-background",
				secondary:
					"bg-secondary text-secondary-foreground hover:bg-secondary/90 aria-expanded:bg-secondary",
				accent:
					"bg-accent text-accent-foreground hover:bg-accent/90 aria-expanded:bg-accent",
				ghost:
					"hover:bg-background hover:text-foreground aria-expanded:bg-background",
				destructive:
					"bg-destructive text-destructive-foreground hover:bg-destructive/90 focus-visible:border-destructive focus-visible:ring-destructive/20",
				link: "text-primary underline-offset-4 hover:underline",
			},
			size: {
				default:
					"h-11 gap-2 px-4 has-data-[icon=inline-end]:pr-3 has-data-[icon=inline-start]:pl-3",
				xs: "h-11 gap-1 px-2 text-xs has-data-[icon=inline-end]:pr-1.5 has-data-[icon=inline-start]:pl-1.5 [&_svg:not([class*='size-'])]:size-3",
				sm: "h-11 gap-1.5 px-3 text-sm has-data-[icon=inline-end]:pr-2 has-data-[icon=inline-start]:pl-2 [&_svg:not([class*='size-'])]:size-3.5",
				lg: "h-12 gap-2 px-6 has-data-[icon=inline-end]:pr-5 has-data-[icon=inline-start]:pl-5",
				icon: "size-11",
				"icon-xs": "size-11 [&_svg:not([class*='size-'])]:size-3",
				"icon-sm": "size-11 [&_svg:not([class*='size-'])]:size-3.5",
				"icon-lg": "size-12",
			},
		},
		defaultVariants: {
			variant: "default",
			size: "default",
		},
	},
);

function Button({
	className,
	variant = "default",
	size = "default",
	asChild = false,
	...props
}: React.ComponentProps<"button"> &
	VariantProps<typeof buttonVariants> & {
		asChild?: boolean;
	}) {
	const Comp = asChild ? Slot.Root : "button";

	return (
		<Comp
			data-slot="button"
			data-variant={variant}
			data-size={size}
			className={cn(buttonVariants({ variant, size, className }))}
			{...props}
		/>
	);
}

export { Button };
