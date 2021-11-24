
Objeto de la prueba: solve() y sus funcionalidades, generar() y sus funcionalidades, validar_kakuro()
Otros elementos integrados en la prueba: printCellMatrix, read_kakuro_file para obtener Kakuros y mostrarlos por pantalla
Driver: DriverCapaDominioKakuro

	------------------------------------------------------------------------------------

		solve() y sus funcionalidades

Ficheros de Datos necesarios: kakuro1,kakuro2,kakuro3, kakuro4, testCheckRun, testtakeFilaColumna
Valores estudiados:
	Kakuro1: Kakuro imposible de resolver.
	Resultado Esperado: el solve informa de que el kakuro no tiene solución.
 
	Kakuro2: Kakuro de dificultad normal genérico, se espera que el solve encuentre la solución y ésta se muestre por pantalla
	Solución:
		*,C19,C16,C5,C7,*,C39,C7,C34
		F25,4,9,5,7,C12F15,3,7,5
		F15,8,7,C23,C22F10,1,9,F6,6
		F7,7,F20,1,5,6,8,C18F8,8
		*,C11,C9F26,2,9,5,1,6,3
		F17,2,1,6,8,C9F13,2,4,7
		F17,9,5,3,C9F22,9,5,7,1
		*,C4F15,3,4,8,C6F12,7,1,4
		F4,4,F18,7,1,6,4,*,*
	Kakuro3: Kakuro de diferentes dimensiones para filas y columnas, se espera que el solve encuentre la solución y ésta se muestre por pantalla
	Solución:
		*,*,C9,C7,C11
		*,F9,1,3,5
		*,F18,8,4,6
	testCheckRun: Contiene diferentes valores que puede recibir la función.
	Resultado Esperado:
		-false
		-true
		-true
		-false
		-false

	testtakeFilaColumna: Recibe la posición de diferentes casillas por pantalla y el valor que queremos introducir para un Kakuro determinado.
	Resultado Esperado:
		-10,45
		-16,11
		-30,20
		-6,13

	------------------------------------------------------------------------------------

		generar() y sus funcionalidades

Ficheros de Datos necesarios: testGenerar, testGenerarResolver

	testGenerar: las 3 opciones de dificultad posibles, el programa deberá generar 1 kakuro válido para cada opción
	Resultado Esperado: se muestran los 3 kakuros generados por pantalla.

	testGenerarResolver: las 3 opciones de dificultad posibles, el programa deberá generar 1 kakuro válido para cada opción y después deberá resolverlos.
	Resultado Esperado: se muestran los 3 kakuros generados por pantalla junto a sus soluciones.

	------------------------------------------------------------------------------------

		validarkakuro()

Ficheros de Datos necesarios: testValidarKakuro1
	-tiene un kakuro genérico
	-Resultado esperado:
		true.

	------------------------------------------------------------------------------------

Operativa de los tests:
En primer lugar, se prueba el solver.
	-lee los archivos indicados
	-En caso de no ser capaz de solucionar un kakuro de los juegos de pruebas se llama a los tests de takefila, takecolumna y checkrun (esto lo vemos en el caso de kakuro1)
	-En caso de poder solucionarlo se imprimirá el resultado por pantalla
Seguidamente se prueba el generar:
	-lee los archivos indicados
	-se muestran los 3 kakuros generados por pantalla
	-genera 3 kakuros más para generarResolver utilizando el archivo indicado
	-se imprime las soluciones por pantalla
Para finalizar, se prueba el validarKakuro
	-lee el archivo indicado
	-se muestra el resultado por pantalla