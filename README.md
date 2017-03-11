# deutsch-lernkarten

A learning tool for German Nouns (Nomen) and Verbs (Verben). Largely this body of knowledge is memorization of the "Nomen mit Artikel" and "Verben mit Vokal Wechsel" which can take advantage of the flashcard style of presenting information, along with spaced repetition.

## Future Ideas

### Monetization

Anonymous users will be able to cycle through the cards without any tracking of progress.

Paid accounts will have
- tracking of progress for cards with spaced repetition
- tagging of card into sets
- ability to add cards (front and back)

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```
