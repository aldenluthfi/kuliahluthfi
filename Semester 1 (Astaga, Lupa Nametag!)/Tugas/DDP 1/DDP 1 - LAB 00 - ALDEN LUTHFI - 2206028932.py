import turtle
from math import sqrt

turtle.getscreen()
turtle.hideturtle()

#fd = forward, lt = left, rt = right, bk = backward
crush_si_kura_kura_madura = turtle.Turtle()
crosh_si_penyu_sunda = turtle.Turtle()

crush_si_kura_kura_madura.shape("turtle")
crosh_si_penyu_sunda.shape("turtle")

crush_si_kura_kura_madura.color("#D90429")
crosh_si_penyu_sunda.color("#040F57")

crush_si_kura_kura_madura.fillcolor("#D90429")
crosh_si_penyu_sunda.fillcolor("#040F57")

crush_si_kura_kura_madura.begin_fill()
crosh_si_penyu_sunda.begin_fill()

crosh_si_penyu_sunda.up()
crosh_si_penyu_sunda.fd(20)
crosh_si_penyu_sunda.down()

crush_si_kura_kura_madura.lt(90)
crush_si_kura_kura_madura.fd(20)
crosh_si_penyu_sunda.fd(70)

crush_si_kura_kura_madura.lt(90)
crush_si_kura_kura_madura.fd(20)

crush_si_kura_kura_madura.rt(45)
crush_si_kura_kura_madura.fd(20*sqrt(2))
crosh_si_penyu_sunda.lt(90)
crosh_si_penyu_sunda.fd(60)

crush_si_kura_kura_madura.rt(45)
crush_si_kura_kura_madura.fd(20)
crush_si_kura_kura_madura.rt(45)
crush_si_kura_kura_madura.fd(20*sqrt(2))
crosh_si_penyu_sunda.lt(90)
crosh_si_penyu_sunda.fd(50)

crush_si_kura_kura_madura.rt(45)
crush_si_kura_kura_madura.fd(20)
crosh_si_penyu_sunda.rt(90)
crosh_si_penyu_sunda.fd(20)

crush_si_kura_kura_madura.lt(90)
crush_si_kura_kura_madura.fd(20)
crosh_si_penyu_sunda.rt(90)
crosh_si_penyu_sunda.fd(50)

crush_si_kura_kura_madura.lt(90)
crush_si_kura_kura_madura.fd(30)
crush_si_kura_kura_madura.lt(45)
crush_si_kura_kura_madura.fd(30*sqrt(2))
crosh_si_penyu_sunda.lt(90)
crosh_si_penyu_sunda.fd(20)

crush_si_kura_kura_madura.lt(45)
crush_si_kura_kura_madura.fd(40)
crush_si_kura_kura_madura.lt(45)
crush_si_kura_kura_madura.fd(30*sqrt(2))
crosh_si_penyu_sunda.lt(90)
crosh_si_penyu_sunda.fd(70)

crush_si_kura_kura_madura.rt(135)
crush_si_kura_kura_madura.bk(30)
crush_si_kura_kura_madura.end_fill()
crush_si_kura_kura_madura.hideturtle()

crosh_si_penyu_sunda.lt(90)
crosh_si_penyu_sunda.fd(60)

crosh_si_penyu_sunda.lt(90)
crosh_si_penyu_sunda.fd(50)

crosh_si_penyu_sunda.rt(90)
crosh_si_penyu_sunda.fd(20)

crosh_si_penyu_sunda.rt(90)
crosh_si_penyu_sunda.fd(50)

crosh_si_penyu_sunda.rt(90)
crosh_si_penyu_sunda.bk(20)
crosh_si_penyu_sunda.end_fill()
crosh_si_penyu_sunda.hideturtle()

turtle.exitonclick()