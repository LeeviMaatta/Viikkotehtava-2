# Viikkotehtävä 1 – TODO-lista

## Yleiskuvaus

Tämä Android-sovellus on toteutettu Jetpack Composella ilman XML-layoutteja. Sovellus esittää yksinkertaisen TODO-listan, jossa tehtäviä voidaan lisätä, merkitä valmiiksi, suodattaa ja järjestää.

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

Sovellus käyttää mock-dataa (5–10 tehtävää), jotka alustetaan sovelluksen käynnistyessä listaan. Tätä dataa käytetään näkymän testaamiseen ja toiminnallisuuksien demonstrointiin.

---

## Kotlin-funktiot

### addTask

Lisää uuden tehtävän listan perään.

```kotlin
fun addTask(list: MutableList<Task>, task: Task)
```

---

### toggleDone

Kääntää yksittäisen tehtävän `done`-tilan (true ↔ false) annetun id:n perusteella.

```kotlin
fun toggleDone(list: MutableList<Task>, id: Int)
```

---

### filterByDone

Palauttaa listasta vain ne tehtävät, joiden `done`-tila vastaa annettua arvoa.

```kotlin
fun filterByDone(list: List<Task>, done: Boolean): List<Task>
```

---

### sortByDueDate

Järjestää tehtävälistan deadline-päivämäärän mukaan.

```kotlin
fun sortByDueDate(list: List<Task>): List<Task>
```

---

## Käyttöliittymä (Compose)

* **HomeScreen** näyttää tehtävälistan
* Tehtävät esitetään `Column`- ja `Row`-rakenteilla
* Käyttäjä voi:

  * lisätä uusia tehtäviä lomakkeella
  * merkitä tehtäviä valmiiksi Checkboxilla
  * suodattaa vain valmiit tehtävät

Kaikki UI on toteutettu Jetpack Composella ilman XML-layoutteja.

---

## Teknologiat

* Kotlin
* Jetpack Compose
* Android Studio (Compose Template)
