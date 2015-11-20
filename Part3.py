from subprocess import call

m = 2000000
k = 1

while m <= 8000000:
		
	k = 1
	while k <= 3:		
			
		call(["java", "BloomFilterMain", "nums.txt", "4000000", str(m), str(k)])
		
		print "Done with " + " m=" +  str(m) + " k=" + str(k)
			
		k += 1
		
	m *= 2