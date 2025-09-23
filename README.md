# Notes on the Module "Base"

This module is a small helper library that has been spun off from the API module, the main library. 

This might seem overly complicated, but in fact, it was necessary, because the author uses it in another, external project as well, without using the API there.

## Major Changes 
### V. 0.7 &rarr; 0.8
None (not in this module).

### V. 0.6 &rarr; 0.7
* `KMMQualifSecID`: Fixed bug in method `parse()`
* `KMMPriceID`: New method `getPricePairID()`

### V. 0.5 &rarr; 0.6
* `FixedPointNumber`: Ironed out some inconsistencies: Some methods would change the (value of the) object itself, some others would not and instead generate a new one. Now, every calc-operation changes the (value of the) object itself. 

  This admittedly leads to less-than-beautiful code in the other modules, because you now have to use the method `copy()` a lot of times, but we had to do so before the changes anyway here and there, and at least it's consistent now.

* Better test coverage: Now, I feel much better about it.

* Finally: Moved package `numbers` to `SchnorxoLib` (and thus merged it with the almost 100%-redundant code in the sister project's according package).

* Fixed bug with set() methods in `KMMQualifCurrID` / `KMMQualifSecID`.

### V. 0.4 &rarr; 0.5
* Created, spun off from the API module.

* Better test coverage

## Planned
./.

## Known Issues
./.
