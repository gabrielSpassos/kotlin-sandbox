# TAX SERVICE POC

## Requirements
- Build a TAX system where different products have different tax per state and year.
- No database
- ZERO persistence
- no maps / data structure
  - only list
- no dependency between classes
  - build these dependencies somewhere else

## Functionalities
- [x] which is the 2023 tax from a state?
- [x] which is the current tax of the product on specific state?
- [x] get all taxes from 2023 year
- [x] add taxes for 2024

## Suggestions
- chain
  - https://refactoring.guru/design-patterns/chain-of-responsibility
- specification
  - https://medium.com/@carlosraphael/specification-design-pattern-in-java-8-bac6f5f943bc
  - https://dzone.com/articles/specification-pattern-quickly
- val taxes = TaxFactory.getTaxes(2023)
  - run specification

## Class diagram
![tax-service-class-diagram.png](tax-service-class-diagram.png)

## TODOs:

- [x] Class diagram
- [x] Code
- [x] Tests





