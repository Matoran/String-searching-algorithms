#!/bin/bash
for pattern in 1212 wwww bonsbonsbons 1111abt1111ab; do

   	printf "pattern $pattern\n";
	for algo in {1..4}; do		
		java Main $pattern $algo Tests/test.txt;
	done
	printf "\n\n\n";
done 
