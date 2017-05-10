#!/bin/bash
for pattern in 1212 wwww bonsbonsbons 1111abt1111ab abt1111a bon bons; do
   	printf "pattern $pattern\n";
	for algo in {1..1000}; do
		java Main $pattern $algo Tests/test.txt;
	done
	printf "\n\n\n";
done 
