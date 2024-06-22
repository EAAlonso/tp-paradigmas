# Consigna

## Presentación
Se solicita construir un sistema de administración y gestión de criptomonedas que le permita 
al usuario (Administrador o Trader) realizar transacciones de estas.
La cantidad de criptomonedas presentes en el sistema es variable, inicialmente todas se 
encuentran confinadas en el archivo criptomonedas.csv Una posible representación del 
archivo podría ser:
| Nombre | Símbolo | Valor |
| ------ | ------- | ----- |
| Bitcoin | BTC | 1245.00 |
| Ethereum | ETH | 1028.00 |
| Tether | USDt | 724.50 |

Cada criptomoneda se identifica por su nombre, símbolo, precio base en dólares a la fecha 
actual.
En un segundo archivo mercados.csv se encuentra la información actual de cada 
criptomoneda en función de los parámetros del mercado (Capacidad, volumen en las 24 horas 
, Variación en los últimos 7 días). Un posible formato del archivo podría ser:
| Símbolo | Capacidad | Volumen 24 hs | Variación 7 días |
| ------- | --------- | ------------- | ---------------- |
| BTC | 1,38 | 34,66% | +13,84% |
| ETH | 450,40 | 27,91% | +2,59% |

Dicho archivo contiene la misma cantidad de registros que el archivo criptomonedas.csv
Existen dos tipos de usuarios en el sistema: Un administrador que contiene como atributos 
un nombre y un perfil. El otro usuario es un trader que contiene como atributos el nombre,, 
el número de cuenta bancaria, el nombre del Banco y el saldo actual (Considerar siempre 
positivo). Dichos usuarios se encuentran en el archivo usuarios.csv

Un posible formato podría ser:

| Nombre | Tipo/NroCuenta | Nombre Banco | Saldo |
| ------ | -------------- | ------------ | ----- |
| jperez | administrador | | |
| hrizo24 | 7824621 | Banco Río | 78000.00 |
| mlopez | 5821621 | Banco Francés | 800000.00 |
| rlopez | administrador | | |

En primera instancia el sistema debe solicitar el ingreso de los datos del usuario y determinar 
su perfil. En el caso de que el usuario sea administrador, el sistema exhibe un menú para la 
gestión de las criptomonedas (dar de alta, modificar, eliminar, consultar, ver mercado actual), 
y al usuario trader la posibilidad de consultar, ver mercado actual, recomendar, comprar y 
vender (también en formato de menú). En el caso de que el trader no se encuentre registrado 
en el sistema, deberá registrarse ingresando su nombre de usuario y los datos de su cuenta 
bancaria asociada: Número de cuenta, Nombre del banco y el saldo. 
Un posible formato de menú para el administrador podría ser el siguiente:

Menú de opciones
----------------
  1) Crear Criptomoneda
  2) Modificar Criptomoneda
  3) Eliminar Criptomoneda
  4) Consultar Criptomoneda
  5) Consultar estado actual del mercado
  6) Salir

Ingrese su opción (1 - 6): 

Las actualizaciones de los parámetros de las criptomonedas generan un impacto en el archivo 
mercados.csv del siguiente modo:
* Si el administrador desea agregar una criptomoneda en el archivo 
criptomonedas.csv, debe ingresar el nombre, el símbolo y el precio dólar base. La 
nueva criptomoneda se debe adicionar inmediatamente al archivo mercados.csv, en 
este caso los parámetros de capacidad, volumen en las 24 horas y variación en los 
últimos 7 días debe contener su valor base que es 1% en alza, la capacidad debe ser 
inicialmente 500. 
Nota: Si la criptomoneda que se desea dar de alta existe en el archivo 
criptomonedas.csv, se debe emitir un mensaje aclaratorio indicando que no se 
puede agregar y se debe consultar al usuario si desea modificar algún 
parámetro de esta, en caso afirmativo el sistema lo redireccionará a dicha 
funcionalidad.
* Si el administrador modifica algún parámetro de la criptomoneda (nombre, símbolo o 
precio dólar base), se deberá actualizar el símbolo de la criptomoneda en el archivo 
mercados.csv
* Si el administrador elimina una criptomoneda del archivo criptomonedas.csv también 
se debe eliminar del archivo mercados.csv
*  Si el administrador o trader desea consultar los datos de una criptomoneda, el sistema 
debe confinar la información de los archivos criptomonedas.csv y mercados.csv

Una posible representación de la consulta podría ser
Nombre: Bitcoin     Símbolo: BTC     Precio en dólares: 70.124,4
Datos del mercado:
Capacidad: 1,38     volumen en las últimas 24 horas: 34,66%     Variación en los últimos 7 días: +13,84%

Por otra parte, el sistema ofrece al usuario trader realizar transacciones tales como comprar, 
vender, consultar y recomendar criptomonedas.
* Para la sección de compras, se debe seleccionar el símbolo de la criptomoneda y el 
total a comprar, el sistema debe exhibir el valor en dólares de esta y el total que se 
puede comprar (capacidad).
  *  En caso de que el usuario confirme la compra, la capacidad se debe restar en 
el archivo mercados.csv y aumentar un 5% los parámetros volúmen en las 
últimas 24 horas y variación en los últimos 7 días del mismo archivo. Si la 
cantidad de compras de una criptomoneda supera las 1000 entonces se debe 
aumentar su precio en dólares un 10%.
  *  El proceso para confirmar la compra es el siguiente: El sistema debe 
verificar que el usuario disponga de dinero en su cuenta bancaria, en caso 
contrario rechaza la operación y le solicitará al usuario ingresar el dinero 
faltante. Si la compra es satisfactoria, el sistema debe actualizar el saldo del
usuario (disminuir el saldo) y almacenar todas las criptomonedas compradas 
hasta el momento por el usuario (a modo de archivo histórico) con el siguiente 
diseño: Símbolo de la criptomoneda y la cantidad comprada. 
En el caso de que exista la criptomoneda en el archivo, solo se debe actualizar 
la cantidad (acumulando la anterior con la actual). El nombre de dicho archivo 
debe ser NombreDeUsario_historico.csv.
Por último se debe actualizar el saldo del usuario en su cuenta bancaria.
Un posible ejemplo del archivo histórico podría ser

hrizo_historico.csv

BTC , 35
USDT , 5
...
ETH , 23
*  Para el caso de la venta, se debe seleccionar el símbolo de la criptomoneda y el 
sistema debe exhibir la cantidad máxima que puede vender. Dicha cantidad se debe 
obtener del archivo histórico del usuario. Luego, el usuario debe ingresar la cantidad 
a vender, si dicha cantidad es superior a la indicada se debe emitir un mensaje de 
error y continuar con la ejecución del menú.
  *  En el caso de que se confirme la venta, se debe aumentar la capacidad en el 
archivo de mercados.csv de dicha criptomoneda y disminuir un 7% el valor 
los parámetros volúmen en las últimas 24 horas y variación en los últimos 7 
días. Luego de realizada la operación se debe actualizar el archivo histórico 
del usuario modificando la cantidad (disminuir la cantidad) y actualizar el saldo 
del usuario.
*  En el caso de que el usuario augure una recomendación de compra por parte del 
sistema, el mismo realizará una evaluación estadística entre la criptomoneda de 
mayor cotización y la cantidad disponible en el archivo de mercados.csv
El cálculo que se debe realizar es:
(Cantidad disponible de la criptomoneda de mayor valor / precio en dólares de la criptomoneda) * 
100
El sistema recomendará aquella criptodivisa de mayor porcentaje, tomando como 
referencia la del archivo criptomonedas.csv
*  El sistema le ofrecerá al usuario consultar/visualizar el archivo histórico de sus 
transacciones (Estado actual de las criptomonedas compradas y vendidas) ordenado 
alfabéticamente por símbolo o por cantidad en modo descendente.
Un posible formato de menú para el usuario trader podría ser el siguiente:

Menú de opciones
----------------
  1) Comprar Criptomonedas
  2) Vender Criptomonedas
  3) Consultar Criptomoneda
  4) Recomendar Criptomonedas
  5) Consultar estado actual del mercado
  6) Visualizar archivo de transacciones (histórico).
  7) Salir

Ingrese su opción (1 - 7): 
