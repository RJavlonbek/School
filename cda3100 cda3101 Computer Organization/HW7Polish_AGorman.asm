# Adam Gorman HW7
#	
# Although it was a relatively easy assignment, I often find it beneficial to myself
# to write the program in c++ or java first. To fully grasp the logic behind it.
# I know I can write it straight in mips, but it saves me a lot of time solving the logic.
# I included the cpp for it, and for students who might struggle, it might be beneficial
# for them to write it in c++ so that when its time to write it in mips, they will spend time
# writing mips code, and not trying to resolve logic and flow issues. That is if they are very 
# comfortable in another programming language. 
#
#	I ran into no problems writing the mips code for this. 
# 	Code is documented along the way. 
#
# insert your terms procedure and your Polish function here
##################################################################
terms:
	addi $sp, $sp, -16	# save ra for later
	sw $ra, 0($sp)
	sw $t0, 4($sp)
	sw $t1, 8($sp)
	sw $t2, 12($sp)
	
	move $t0, $a0 #save a0 for later
	
	la	$a0, t_msg	# print first 16 terms
	li	$v0, 4
	syscall
	
	move $a0, $t0 	#prints the first term
	li	$v0, 1
	syscall	
	
	li $a0, 32 		#print a space
	li	$v0, 11
	syscall
	
	li $t1, 0 	# t1 = 0 
	li $t2, 15 	# t2 = 15 used for while loop
	##########################
	L1:  ble  $t2, $t1, L1_exit # branch if ( t2 <= t1 ) 
		
	move $a0, $t0		# restore saved input variable
	jal polish			# and go to polish function
	move $t0, $v0
	############################
	
	move $a0, $t0 		# print returned value from polish
	li	$v0, 1
	syscall
	
	li $a0, 32			# prints space
	li	$v0, 11
	syscall
	
	move $a0, $t0		# restore a0 for next polish function call
	
	addi $t1, $t1, 1		# t1 increment
	j    L1             # jump back to top of loop 
	
	L1_exit:
	lw $ra, 0($sp)		# restore ra
	lw $t0, 4($sp)
	lw $t1, 8($sp)
	lw $t2, 12($sp)
	addi $sp, $sp, 16
jr $ra	

##################################################################
polish:
	addi $sp, $sp, -16	# save ra for later
	sw $ra, 0($sp)
	sw $t7, 4($sp)
	sw $t6, 8($sp)	# I dont typically t's are throw away values and you use s for more important values, but 
	sw $t5, 12($sp)	# But this is how I did it, and polish/terms are both copyable to other programs still
	
	li $t5, 0
	L2:
	ble  $a0, $0, L2_exit
		move $t7, $a0		# save a0 to t7
	
		li $t6, 10			# set t6 to 10 
		div $a0, $t7, $t6	# a0 = t7 / t6 (truncate)
		rem $t6, $t7, $t6	# t6 = t7 mod t6
		
		mul $t6, $t6, $t6 # t6 = t6*t6  (remainder)
		add $t5, $t5, $t6 # add to new sum
		j L2
	L2_exit:
	move $v0, $t5 #return sum
	
	
	lw $ra, 0($sp)		# restore ra
	lw $t7, 4($sp)
	lw $t6, 8($sp)
	lw $t5, 12($sp)
	addi $sp, $sp, 16
jr $ra	
##############################################

# following code is not written by me, 
# and is unchanged from given template


# Driver program provided by Stephen P. Leach -- written 12/15/08

main:	la	$a0, intro	# print intro
	li	$v0, 4
	syscall

loop:	la	$a0, req	# request value of n
	li	$v0, 4
	syscall

	li	$v0, 5		# read value of n
	syscall

	ble	$v0, $0, out	# if n is not positive, exit

	move	$a0, $v0	# set parameter for terms procedure

	jal	terms		# call terms procedure

	j	loop		# branch back for next value of n

out:	la	$a0, adios	# display closing
	li	$v0, 4
	syscall

	li	$v0, 10	# exit from the program
	syscall

	.data
	
intro:	.asciiz  "Welcome to the Polish sequence tester!"
req:	.asciiz  "\nEnter an integer (zero or negative to exit): "
t_msg:	.asciiz  "First 16 terms: "
adios:	.asciiz  "Come back soon!\n"


