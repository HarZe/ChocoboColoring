# ChocoboColoring

This simple algorithm aims to solve the problem of feeding a Chocobo in FFXIV in order to modify its color as desired. Providing the current color of your Chocobo and the new one you want, you'll get the berries you need and the feeding order that ensures a successful color modification.

### Why?

I have seen many websites where you can check the total berries you need to use to modify the color of a Chocobo. They use to recommend to alternate berries to avoid getting out of the RGB value range (from 0 to 255), which will cause the modification to fail. This algorithm ensures that you are always within those values, therefore you have not to worry about wasting berries and time.

## Usage

Execute the main function (or the compiled jar) with two arguments, being the first the name of the current color of your chocobo, and the second the target color. A correct input will provide the berries you need to feed the chocobo (totals and order), otherwise it will print the available colors.

### Usage example

*java -jar chocobo-coloring-0.1.jar "Desert Yellow" "Ash Gray"*

## License

LGPL v3.0
