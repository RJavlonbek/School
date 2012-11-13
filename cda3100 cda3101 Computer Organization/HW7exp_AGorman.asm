# Adam Gorman HW7
#	
# Originally I ran ino -1. #IND0000 floaing poin excepion 15 error around e^12.62. 
# e^12.61 ran ok, and was equal o jus shy of 300,000
# I was baffled for quite a long time
# I realized it had to do with my x^n / n! section
# I was doing the numerator or expontent first, then dividing by the complete factorial,
# It caused the numerator to get overly large too fast
# I fixed it by doing x/n * x/(n-1)  etc.. as to keep the size of this number down
#
# I believe that this is the same reason that its running into an issue into 
# higher numbers around 80-90 ish
# the 2 major downsides of floating points is ran into here
# there maximum exponent size is +127 
# 90 ish is approximately where it breaks down,  
# I believe that one of my floating point go out of range and cause an error
# Also, one of the downsides to floating points is that when it gets into very high ranges
#its capabilities of precision start to break down
# It cannot represent very large or very small numbers very well. Itll go with a "best fit" approach
# it is why its crucial that a programmer never use double or floats for anything financial related
##################################################################

eeee:
	#$t5, $f1, $f5, $f6, $f7, $f9, $f10, $f13, $f14, $f29, $f30, $f31
	
	addi $sp, $sp, -48	# save for later
	sw $t5, 0($sp)
	sw $ra, 4($sp)		# save ra for later
	swc1 $f5, 8($sp)
	swc1 $f6, 12($sp)
	swc1 $f7, 16($sp)	#I originally didn't include these
	swc1 $f9, 20($sp)	#
	swc1 $f10, 24($sp)	#Proper use of return instructions [including use of stack] (0-2,3-4,5-5)
	swc1 $f13, 28($sp)	#Proper design of a subprogram meet specified requirements 
	swc1 $f14, 32($sp)	#
	swc1 $f29, 36($sp)	#is what made me add, its unnecesary for this particular driver program
	swc1 $f30, 40($sp)	#however now this function can be copied/pasted into other various programs and not break them
	swc1 $f31, 44($sp)	
			

	## SETUP VARIABLES
	
	li $t5, 0 		# false, negative number
	li.s $f29, 0.000000000000001	#constant 1e-15 
	li.s $f30, 0.0	#constant 0
	li.s $f31, 1.0	#constant 1
	li.s $f12, 0.0	#f12 = 1
	li.s $f7, 1.0	#f7 = 1  current step
	li.s $f6, 0.0	#f6 = 1  counter1
	li.s $f5, 1.0	#f5 = 1  counter2
	mov.s $f9, $f1	#f9 = f1
	
	
	c.lt.s  $f30, $f1
	bc1t L3

	li $t5, 1 # if f1 is less than f30 its negative!	
	
L3:
	c.lt.s  $f7, $f29 	#while # 1e-15 < f7, continue
	bc1t L3_exit
	
		abs.s $f9, $f1 #make it positive
		li.s $f14, 1.0	#resetting variable
		li.s $f10, 1.0
		for_loop1:
			c.lt.s $f6, $f10  # f6 <= f10 == !(f10 > f6)
			bc1t end_for_loop1
			
			div.s $f13, $f9, $f5		# x / !
			mul.s $f14, $f14, $f13;   # x^n / n * x^(n-1) / (n-1)
			
			sub.s $f5, $f5, $f31 #decrease the factorial counter
		
			add.s $f10, $f10, $f31 # i = i + 1
			j for_loop1
			
		end_for_loop1:
		
		mov.s $f9, $f14  
		add.s $f6, $f6, $f31
		mov.s $f5, $f6
		
		add.s $f12, $f12, $f9  #this is the current e^x as it builds
		add.s $f7, $f12, $f9	#this is to help check step size, because we don't add it, if it is too small
		div.s $f7, $f9, $f7  #this particular steps relative to e^x for difference/variance to the 1e-15 comparsion
		j L3
			
	L3_exit:
		beq $t5, $0, go_here
		div.s $f12, $f31, $f12
	go_here:
	
	
		# restore any existing variables
		lw $t5, 0($sp)
		lw $ra, 4($sp)		# restore ra
		lwc1 $f5, 8($sp)
		lwc1 $f6, 12($sp)
		lwc1 $f7, 16($sp)
		lwc1 $f9, 20($sp)
		lwc1 $f10, 24($sp)
		lwc1 $f13, 28($sp)
		lwc1 $f14, 32($sp)	# RESTORE!
		lwc1 $f29, 36($sp)	
		lwc1 $f30, 40($sp)
		lwc1 $f31, 44($sp)
		addi $sp, $sp, 48	
		
		jr $ra	
		
##############################################


main:	
	la	$a0, intro	# print intro
	li	$v0, 4
	syscall
		
	li.s $f3, 999.0	

loop:	
	la	$a0, req		# request value of n
	li	$v0, 4
	syscall

	li	$v0, 6		# read float value of n
	syscall
	
	mov.s $f1, $f0	# save input as original number, do not change
	
	c.eq.s $f1,$f3
	bc1t out 		# if f1 equals 999, branch out and exit

	mfc1 $a0, $f1
	jal eeee		# e^x funtion!!!
	mov.s	$f2, $f12 #save for later
	
	la	$a0, t_msg	# Our approximation for e^
	li	$v0, 4
	syscall
	
	mov.s $f12, $f1	#  The number
	li	$v0, 2
	syscall
	
	la	$a0, t_msg2	#  is 
	li	$v0, 4
	syscall
	
	mov.s	$f12, $f2 # set value for e^x
	li	$v0, 2
	syscall
	
	j	loop		# branch back for next

out:	
	la	$a0, adios	# display closing
	li	$v0, 4
	syscall

	li	$v0, 10	# exit from the program
	syscall

	
	.data
intro:	.asciiz  "Let's test our exponential function!"
req:	.asciiz  "\nEnter a value for x (or 999 to exit): "
t_msg:	.asciiz  "Our approximation for e^"
t_msg2:	.asciiz  " is "
adios:	.asciiz  "Come back soon!\n"
