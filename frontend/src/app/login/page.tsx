"use client";
import TripCard from "@/components/TripCard";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import React from "react";

type Props = {};

export default function LoginPage({}: Props) {
	return (
		<div className="flex min-h-[100dvh] flex-col items-center justify-center bg-background px-4 py-8">
			<div className="w-full max-w-md space-y-6">
				<div className="space-y-2 text-center">
					<h1 className="text-3xl font-bold">Welcome to Cab Share</h1>
					<p className="text-muted-foreground">
						Discover the easiest way to share rides and save money.
					</p>
					<div className="text-center text-muted-foreground">
						Cab Share is a platform that connects drivers and passengers to
						share rides and save money.
					</div>
					<div className="space-y-4">
						<Button variant="outline" className="w-full">
							<ChromeIcon className="mr-2 h-5 w-5" />
							Sign in with Google
						</Button>
					</div>
				</div>
				<div className="space-y-2 text-center">
					<h1 className="text-xl font-semibold">Recently Added Trips</h1>
				</div>
				<div className="flex justify-center gap-4 flex-nowrap ">
					<TripCard
						name="John Doe"
						description="Traveling from San Francisco to Los Angeles"
						onClick={() => {}}
						image="/placeholder-user.jpg"
					/>
					<Card className="p-4 hover:bg-muted transition-colors min-w-80">
						<CardContent className="flex flex-col text-center items-center gap-4">
							<Avatar>
								<AvatarImage src="/placeholder-user.jpg" />
								<AvatarFallback>JD</AvatarFallback>
							</Avatar>
							<div className="flex-1 space-y-1">
								<h3 className="font-semibold">Jane Doe</h3>
								<p className="text-sm text-muted-foreground">
									Traveling from Los Angeles to San Diego
								</p>
							</div>
							<Button variant="outline" size="sm">
								Join Ride
							</Button>
						</CardContent>
					</Card>
					<Card className="p-4 hover:bg-muted transition-colors min-w-80">
						<CardContent className="flex flex-col text-center items-center gap-4">
							<Avatar>
								<AvatarImage src="/placeholder-user.jpg" />
								<AvatarFallback>JD</AvatarFallback>
							</Avatar>
							<div className="flex-1 space-y-1">
								<h3 className="font-semibold">John Smith</h3>
								<p className="text-sm text-muted-foreground">
									Traveling from San Jose to San Francisco
								</p>
							</div>
							<Button variant="outline" size="sm">
								Join Ride
							</Button>
						</CardContent>
					</Card>
				</div>
			</div>
		</div>
	);
}

function ChromeIcon(props: any) {
	return (
		<svg
			{...props}
			xmlns="http://www.w3.org/2000/svg"
			width="24"
			height="24"
			viewBox="0 0 24 24"
			fill="none"
			stroke="currentColor"
			strokeWidth="2"
			strokeLinecap="round"
			strokeLinejoin="round"
		>
			<circle cx="12" cy="12" r="10" />
			<circle cx="12" cy="12" r="4" />
			<line x1="21.17" x2="12" y1="8" y2="8" />
			<line x1="3.95" x2="8.54" y1="6.06" y2="14" />
			<line x1="10.88" x2="15.46" y1="21.94" y2="14" />
		</svg>
	);
}
