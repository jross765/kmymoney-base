# Major Changes

## V. 0.9 &rarr; 0.10
Followed the deprecation of `FixedPointNumber` in the module
"(Core) API": 

Deprecated everything that is `FixedPointNumber`-related (cf. previous release).

## V. 0.8.1 &rarr; 0.9
* Introduced `KMMCurrID` (as apposed to `KMMQualifCurrID`). Changed `KMMQualifCurrID` accordingly (class hierarchy/inclusion logic is now
consistent with that of `KMMSecID`/`KMMQualifSecID`).

  *Rationale*: Seen in the narrow context of this module, this 
  provides no additional value -- neither does it in the context
  of all this project's modules. However, it does make sense once 
  you look at the big picture, i.e. *both* projects 
  `JGnuCashLibNTools` and `JKMyMoneyLibNTools` and the symmetry
  that I am trying to maintain between them.

* Some minor improvements (more variants for constructors).

## V. 0.8 &rarr; 0.8.1
Only technical changes, essentially (i.e. code) unchanged.

## V. 0.7 &rarr; 0.8
No changes.

## V. 0.6 &rarr; 0.7
* `KMMQualifSecID`: Fixed bug in method `parse()`

* `KMMPriceID`: New method `getPricePairID()`

## V. 0.5 &rarr; 0.6
* `FixedPointNumber`: Ironed out some inconsistencies: Some methods would change the (value of the) object itself, some others would not and instead generate a new one. Now, every calc-operation changes the (value of the) object itself. 

  **CAUTION: This means that, as opposed to many (all?) other `number`-based Java classes, `FixedPointNumber` is *not* immutable.** 
  Having it that way is, de facto, less of a *design* decision and more of a tribute to the *history* of the projects `JGnuCashLib(NTools)` and `JKMyMoneyLibNTools`
  (and there are other things to say about this class, but let's not digress...).

  This admittedly leads to less-than-beautiful code in the other modules, because you now have to use the method `copy()` a lot of times, but we had to do so before the changes anyway here and there, and at least it's consistent now.

* Better test coverage: Now, I feel much better about it.

* Finally: Moved package `numbers` to `SchnorxoLib` (and thus merged it with the almost 100%-redundant code in the sister project's according package).

* Fixed bug with set() methods in `KMMQualifCurrID` / `KMMQualifSecID`.

## V. 0.4 &rarr; 0.5
* Created, spun off from the API module.

* Better test coverage
