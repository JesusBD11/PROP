Objeto de la prueba: clase Kakuro

Otros elementos integrados: read_kakuro_file

Drivers: DriverKakuro

Ficheros de datos necesarios: kakuro24

Valores estudiados:
	-se le pasa un kakuro genérico para probar todas las constructoras y consultoras.
	Resultado esperado para las consultoras:

	*,C15,C8,*
	F14,?,?,C12
	F1,?,F3,?
	F8,?,F9,?
	Celdas Blancas: 6
	Celdas Negras: 10
	soluciones: []
	ID del Kakuro: 0

Operativa:
	-primero leerá el archivo kakuro24. Seguidamente, DriverKakuro realizará las siguientes funcionalidades en el orden correspondiente:
		Constructor vacío, get del kakuro leído, constructor con valores del kakuro leído.