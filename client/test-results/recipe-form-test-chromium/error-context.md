# Page snapshot

```yaml
- generic [ref=e4]:
  - heading "HOME PAGE" [level=1] [ref=e5]
  - paragraph [ref=e6]: Welcome to the home page!
  - generic [ref=e8]:
    - heading "Liste des recettes" [level=2] [ref=e9]
    - list [ref=e10]:
      - listitem [ref=e11]:
        - heading "Pâtes carbonara" [level=3] [ref=e12]
        - paragraph [ref=e13]: Un grand classique italien à base d'œufs, de pancetta et de parmesan.
      - listitem [ref=e14]:
        - heading "Salade de quinoa" [level=3] [ref=e15]
        - paragraph [ref=e16]: Une salade fraîche et colorée, idéale pour l'été.
      - listitem [ref=e17]:
        - heading "Poulet au curry" [level=3] [ref=e18]
        - paragraph [ref=e19]: Recette exotique parfumée au lait de coco.
  - generic [ref=e21]:
    - heading "Créer une recette" [level=2] [ref=e22]
    - generic [ref=e26]:
      - generic [ref=e27]: Nom de la recette
      - textbox "Nom de la recette" [ref=e28]: Tarte aux poireaux
    - generic [ref=e33]:
      - generic [ref=e34]: Description
      - textbox "Description" [ref=e35]: Une tarte réconfortante!
    - generic [ref=e40]:
      - generic [ref=e41]: Durée (minutes)
      - spinbutton "Durée (minutes)" [ref=e42]: "25"
    - generic [ref=e47]:
      - generic [ref=e48]: Ingrédients (séparés par des virgules)
      - textbox "Ingrédients (séparés par des virgules)" [active] [ref=e49]:
        - /placeholder: "Ex: tomate, basilic"
        - text: p
    - button "Ajouter" [ref=e51] [cursor=pointer]
```