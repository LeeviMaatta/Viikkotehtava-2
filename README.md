# Viikkotehtävä 2 – TODO-lista

## Yleiskuvaus

Tämä Android-sovellus on toteutettu Jetpack Composella ilman XML-layoutteja. Sovellus esittää yksinkertaisen TODO-listan, jossa tehtäviä voidaan lisätä, poistaa, merkitä valmiiksi, suodattaa ja järjestää. Sovelluksessa käyttöliittymä ja sovelluslogiikka on erotettu toisistaan ViewModelin avulla.

---

## Datamalli

### Task

`Task`-data class kuvaa yksittäistä tehtävää.

Kentät:

* **id (Int)**: Tehtävän yksilöllinen tunniste
* **title (String)**: Tehtävän otsikko
* **description (String)**: Tehtävän kuvaus
* **priority (Int)**: Tehtävän prioriteetti
* **dueDate (String)**: Tehtävän deadline (merkkijonona)
* **done (Boolean)**: Tehtävän tila (valmis / ei valmis)

```kotlin
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int,
    val dueDate: String,
    val done: Boolean
)
```

---

## Mock-data

Sovellus käyttää mock-dataa (5 tehtävää), jotka alustetaan TaskViewModel-luokan init-lohkossa. Mock-data mahdollistaa sovelluksen toiminnallisuuksien testaamisen.

---

## ViewModel ja Compose-tilanhallinta

### TaskViewModel
TaskViewModel vastaa tehtävälistan tilasta ja sovelluslogiikasta. Se sisältää tehtävälistan mutableStateOf-tilamuuttujana, jolloin Jetpack Compose pystyy reagoimaan automaattisesti muutoksiin.

ViewModel tarjoaa seuraavat toiminnot:
* **addTask(task: Task)** – lisää uuden tehtävän listaan
* **toggleDone(id: Int)** – vaihtaa tehtävän valmiustilan
* **removeTask(id: Int)** – poistaa tehtävän listasta
* **filterByDone(done: Boolean)** – palauttaa vain valmiit tai keskeneräiset tehtävät
* **sortByDueDate()** – järjestää tehtävät deadlinen mukaan

### Compose-tilanhallinta
Kun ViewModelin tila muuttuu, Compose päivittää automaattisesti vain ne käyttöliittymän osat, jotka riippuvat muuttuneesta tilasta.
Sovelluksessa tilanhallinta perustuu seuraaviin periaatteisiin:
* **ViewModel** säilyttää sovelluksen tilan
* **UI** lukee tilan ViewModelista
* **UI** ei muokkaa dataa suoraan

---

## Miksi ViewModel on parempi kuin pelkkä remember?

Pelkkä remember-tilanhallinta toimii vain Composablen elinkaaren ajan ja tila katoaa esimerkiksi näytön käännössä. ViewModel sen sijaan säilyy konfiguraatiomuutoksissa ja erottaa käyttöliittymän sovelluslogiikasta. Rakenne on myös selkeämpi ja vastuunjako on selkeä(UI ja logiikka). Tällöin sovelluksen laajentaminenkin on helpompaa.

---

## Käyttöliittymä (Compose)

* **HomeScreen** näyttää tehtävälistan
* Tehtävät esitetään **LazyColumn**-komponentilla
* Jokaisella tehtävällä on:
    * Checkbox valmiustilan vaihtamiseen
    * Otsikko
    * Poista-painike
* Käyttäjä voi:
    * lisätä uusia tehtäviä lomakkeella
    * merkitä tehtäviä valmiiksi Checkboxilla
    * suodattaa vain valmiit tehtävät
    * poistaa tehtäviä
    * järjestää tehtävät deadlinen mukaan

---

## Teknologiat

* Kotlin
* Jetpack Compose
* Android Studio (Compose Template)
* Android ViewModel (MVVM)
