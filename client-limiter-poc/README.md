# Client Side Limiter POC

> This is a proof of concept for a client side limiter. 
> The limiter is a simple class that can be used to limit the number of requests made to a server.

# Performance Test

| Limiter       | # of requests | Time Taken (ms) |
|---------------|---------------|-----------------|
| FixedWindow   | 100           | 1               | 
| SlidingWindow | 100           | 1               | 
| FixedWindow   | 1000          | 1               | 
| SlidingWindow | 1000          | 1               | 
| FixedWindow   | 1000000       | 23              | 
| SlidingWindow | 1000000       | 46              | 
| FixedWindow   | 10000000      | 203             | 
| SlidingWindow | 10000000      | 407             | 

### Server

The server is a simple open api about countries. https://restcountries.com/#endpoints-all

##### Response
```json
[
  {
    "name": {
      "common": "Brazil",
      "official": "Federative Republic of Brazil",
      "nativeName": {
        "por": {
          "official": "República Federativa do Brasil",
          "common": "Brasil"
        }
      }
    },
    "tld": [
      ".br"
    ],
    "cca2": "BR",
    "ccn3": "076",
    "cca3": "BRA",
    "cioc": "BRA",
    "independent": true,
    "status": "officially-assigned",
    "unMember": true,
    "currencies": {
      "BRL": {
        "name": "Brazilian real",
        "symbol": "R$"
      }
    },
    "idd": {
      "root": "+5",
      "suffixes": [
        "5"
      ]
    },
    "capital": [
      "Brasília"
    ],
    "altSpellings": [
      "BR",
      "Brasil",
      "Federative Republic of Brazil",
      "República Federativa do Brasil"
    ],
    "region": "Americas",
    "subregion": "South America",
    "languages": {
      "por": "Portuguese"
    },
    "translations": {
      "ara": {
        "official": "جمهورية البرازيل الاتحادية",
        "common": "البرازيل"
      },
      "bre": {
        "official": "Republik Kevreel Brazil",
        "common": "Brazil"
      },
      "ces": {
        "official": "Brazilská federativní republika",
        "common": "Brazílie"
      },
      "cym": {
        "official": "Gweriniaeth Ffederal Brasil",
        "common": "Brasil"
      },
      "deu": {
        "official": "Föderative Republik Brasilien",
        "common": "Brasilien"
      },
      "est": {
        "official": "Brasiilia Liitvabariik",
        "common": "Brasiilia"
      },
      "fin": {
        "official": "Brasilian liittotasavalta",
        "common": "Brasilia"
      },
      "fra": {
        "official": "République fédérative du Brésil",
        "common": "Brésil"
      },
      "hrv": {
        "official": "Savezne Republike Brazil",
        "common": "Brazil"
      },
      "hun": {
        "official": "Brazil Szövetségi Köztársaság",
        "common": "Brazília"
      },
      "ita": {
        "official": "Repubblica federativa del Brasile",
        "common": "Brasile"
      },
      "jpn": {
        "official": "ブラジル連邦共和国",
        "common": "ブラジル"
      },
      "kor": {
        "official": "브라질 연방 공화국",
        "common": "브라질"
      },
      "nld": {
        "official": "Federale Republiek Brazilië",
        "common": "Brazilië"
      },
      "per": {
        "official": "جمهوری فدراتیو برزیل",
        "common": "برزیل"
      },
      "pol": {
        "official": "Federacyjna Republika Brazylii",
        "common": "Brazylia"
      },
      "por": {
        "official": "República Federativa do Brasil",
        "common": "Brasil"
      },
      "rus": {
        "official": "Федеративная Республика Бразилия",
        "common": "Бразилия"
      },
      "slk": {
        "official": "Brazílska federatívna republika",
        "common": "Brazília"
      },
      "spa": {
        "official": "República Federativa del Brasil",
        "common": "Brasil"
      },
      "srp": {
        "official": "Савезна Република Бразил",
        "common": "Бразил"
      },
      "swe": {
        "official": "Förbundsrepubliken Brasilien",
        "common": "Brasilien"
      },
      "tur": {
        "official": "Brezilya Federal Cumhuriyeti",
        "common": "Brezilya"
      },
      "urd": {
        "official": "وفاقی جمہوریہ برازیل",
        "common": "برازیل"
      },
      "zho": {
        "official": "巴西联邦共和国",
        "common": "巴西"
      }
    },
    "latlng": [
      -10.0,
      -55.0
    ],
    "landlocked": false,
    "borders": [
      "ARG",
      "BOL",
      "COL",
      "GUF",
      "GUY",
      "PRY",
      "PER",
      "SUR",
      "URY",
      "VEN"
    ],
    "area": 8515767.0,
    "demonyms": {
      "eng": {
        "f": "Brazilian",
        "m": "Brazilian"
      },
      "fra": {
        "f": "Brésilienne",
        "m": "Brésilien"
      }
    },
    "flag": "🇧🇷",
    "maps": {
      "googleMaps": "https://goo.gl/maps/waCKk21HeeqFzkNC9",
      "openStreetMaps": "https://www.openstreetmap.org/relation/59470"
    },
    "population": 212559409,
    "gini": {
      "2019": 53.4
    },
    "fifa": "BRA",
    "car": {
      "signs": [
        "BR"
      ],
      "side": "right"
    },
    "timezones": [
      "UTC-05:00",
      "UTC-04:00",
      "UTC-03:00",
      "UTC-02:00"
    ],
    "continents": [
      "South America"
    ],
    "flags": {
      "png": "https://flagcdn.com/w320/br.png",
      "svg": "https://flagcdn.com/br.svg",
      "alt": "The flag of Brazil has a green field with a large yellow rhombus in the center. Within the rhombus is a dark blue globe with twenty-seven small five-pointed white stars depicting a starry sky and a thin white convex horizontal band inscribed with the national motto 'Ordem e Progresso' across its center."
    },
    "coatOfArms": {
      "png": "https://mainfacts.com/media/images/coats_of_arms/br.png",
      "svg": "https://mainfacts.com/media/images/coats_of_arms/br.svg"
    },
    "startOfWeek": "monday",
    "capitalInfo": {
      "latlng": [
        -15.79,
        -47.88
      ]
    },
    "postalCode": {
      "format": "#####-###",
      "regex": "^(\\d{8})$"
    }
  }
]
```

### References

* https://0xkishan.medium.com/designing-and-implementing-an-api-rate-limiter-in-java-af34c17145f2
* https://medium.com/@devenchan/implementing-rate-limiting-in-java-from-scratch-fixed-window-and-sliding-window-implementation-a6e8d6407d17