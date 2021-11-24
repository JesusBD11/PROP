#Direccion donde se guardan los .class
OUT_DIR_CLASES = build/clases
OUT_DIR_JAR = ./dist
#Direcciones donde estan los archivos del principales programa
SRC_DIR_CLASES = src/Dominio/clases
SRC_DIR_PERSISTENCIA = src/Persistencia
SRC_DIR_PRESENTACION = src/Presentacion
#Direccion controladores
SRC_DIR_CTRLS = src/Dominio/controladores
#Direccion donde estan los archivos de prueba programa
SRC_DIR_DRIVERS = ./src/Dominio/controladores/Drivers

SRC_DIR_JUNITS = ./src/Dominio/controladores/JUnits

#Para compilar
JC = javac
#Para compilar
JE = java

#nombre del archivo principal
MAIN = Dominio/clases/Kakuro_Game

#add nombre del driver a compilar y ejecutar

DRIVERS = Dominio/controladores/Drivers/DriverPartida \
					Dominio/controladores/Drivers/DriverCapaDominioKakuro \



#TYPES = classes vistas junits
TYPES = class drivers junits

#compila todos los archivos
default:
	make $(TYPES)

#Aumentar reglas si se tiene mas tipos
#compila todos los archivos principales
class:
	javac -d $(OUT_DIR_CLASES) $(SRC_DIR_CLASES)/*.java $(SRC_DIR_PERSISTENCIA)/*.java $(SRC_DIR_PRESENTACION)/*.java $(SRC_DIR_CTRLS)/*.java

#compila todos los drivers
drivers:
	$(JC) -cp $(OUT_DIR_CLASES) -d $(OUT_DIR_CLASES) $(SRC_DIR_DRIVERS)/*.java

#compila todos los junits
junits:
		$(JC) -cp :./lib/*:$(OUT_DIR_CLASES) -d $(OUT_DIR_CLASES) $(SRC_DIR_JUNITS)/*.java


run:
	$(JE) -cp	$(OUT_DIR_CLASES) $(MAIN)


jar:
	jar cfm $(OUT_DIR_JAR)/Kakuro_Game.jar manifest.mf -C $(OUT_DIR_CLASES) .

runJar:
	java -jar $(OUT_DIR_JAR)/Kakuro_Game.jar


#ejecuta todos los drivers
runTests: $(DRIVERS)
$(DRIVERS):
	$(JE) -cp $(OUT_DIR_CLASES) $@

#ejecuta todos los JUnits
runTestsJUnit:
	java -jar ./lib/junit-platform-console-standalone-1.7.0-all.jar --class-path ./build/classes/  --scan-class-path


#Una regla de ejecucion por cada driver

testDriverPartida:
	$(JE) -cp $(OUT_DIR_CLASES) Dominio/controladores/Drivers/DriverPartida

testDriverCapaDominioKakuro:
	$(JE) -cp $(OUT_DIR_CLASES) Dominio/controladores/Drivers/DriverCapaDominioKakuro



# remueve todos los archivos .class
clean:
	$(RM)  $(OUT_DIR_CLASES)/Dominio/clases/*.class
	$(RM)  $(OUT_DIR_CLASES)/Dominio/controladores/*.class
	$(RM)  $(OUT_DIR_CLASES)/Dominio/controladores/Drivers/*.class
	$(RM)  $(OUT_DIR_CLASES)/Dominio/controladores/JUnits/*.class
	$(RM)  $(OUT_DIR_CLASES)/Persistencia/*.class
	$(RM)  $(OUT_DIR_CLASES)/Presentacion/*.class
	$(RM)  $(OUT_DIR_JAR)/*jar
