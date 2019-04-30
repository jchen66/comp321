import sys

# check whether a number is prime
def isPrime(num):
    for i in range(3, int(num**0.5)):
        if num % i == 0:
            return False
    return True

for line in sys.stdin:
    num1, num2 = line.split()
    p = int(num1)
    a = int(num2)
    if not (p == 0 and a == 0):
        # built in function 
        # pow(x, y, z) => x^y%z
        # z is optional 
        answer = pow(a, p, p)
        print('yes') if answer == a and not isPrime(p) else print('no')
