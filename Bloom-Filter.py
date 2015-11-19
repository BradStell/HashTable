from subprocess import call

m = 1000
k = 1
n = 100

while m <= 1000000:
		
	k = 1
	while k <= 3:
				
		n = 100		
		while n <= 10000000:
			
			call(["java", "BloomFilterMain", "nums.txt", str(n), str(m), str(k)])			
			n *= 10
			
			print "Done with " + "n=" + str(n) + " m=" +  str(m) + " k=" + str(k)
			
		k += 1
		
	m *= 10