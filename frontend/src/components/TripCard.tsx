"use client";
import React from "react";
import { Card, CardContent } from "./ui/card";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";
import { Button } from "./ui/button";

type Props = {
	image: string;
	name: string;
	description: string;
	onClick: () => void;
};

export default function TripCard({ image, name, description, onClick }: Props) {
	return (
		<Card className="p-4 hover:bg-muted transition-colors min-w-80">
			<CardContent className="flex flex-col text-center items-center gap-4">
				<Avatar>
					<AvatarImage src={image} />
					<AvatarFallback>JD</AvatarFallback>
				</Avatar>
				<div className="flex-1 space-y-1">
					<h3 className="font-semibold">{name}</h3>
					<p className="text-sm text-muted-foreground">{description}</p>
				</div>
				<Button variant="outline" size="sm" onClick={onClick}>
					Join Ride
				</Button>
			</CardContent>
		</Card>
	);
}
