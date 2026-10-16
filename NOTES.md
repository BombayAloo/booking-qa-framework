# Notatki z nauki – Booking QA Framework

Dziennik nauki do planu „Senior Automation Engineer”. Służy do powtórek przed sesjami powtórkowymi (5, 15, 25, 30, 37, 43) i przed rozmowami rekrutacyjnymi.

Zasady:
- Po każdej sesji: 3–5 punktów „czego się nauczyłem” i co najmniej 3 pytania rekrutacyjne z odpowiedziami.
- Pytanie, na które nie umiem odpowiedzieć bez zaglądania do notatek, trafia do sekcji „Do powtórki”.
- Nigdy nie wpisuję tu haseł, tokenów ani kluczy API (wyjątek: publiczne hasło ćwiczeniowe Restful Booker).

---

## Postęp

| Sesja | Temat | Data | Status |
| --- | --- | --- | --- |
| 0 | Przygotowanie środowiska (CachyOS, Docker, zmienne) | 2026-10-05 | ✅ |
| 1 | Środowisko Maven i pierwszy test | 2026-10-06 | ✅ |
| 2 | OOP w praktyce | 2026-10-08 | ✅ |
| 3 | Kolekcje i generyki | | |

---

## Do powtórki

Odhaczam, gdy umiem wytłumaczyć temat na głos bez notatek.

- [ ] Różnica `equals()` vs `compareTo()` w `BigDecimal`
- [ ] Kiedy `git restore --staged`, a kiedy `git rm --cached`
- [ ] Fazy cyklu życia Mavena po kolei
- [ ] Zmienna zwykła vs eksportowana (fish)
- [ ] `git fetch` vs `git pull`
- [ ] Gałąź lokalna vs zdalna vs śledzona (upstream)
- [ ] Dlaczego JUnit tworzy nową instancję klasy testowej dla każdego testu
- [ ] Interfejs vs klasa abstrakcyjna – 3 różnice i przykład z projektu
- [ ] Cztery filary OOP – każdy z przykładem z mojego kodu
- [ ] Kompozycja vs dziedziczenie – dlaczego `BaseTest` zniknie w sesji 8
- [ ] Modyfikatory dostępu: `private`, brak (package-private), `protected`, `public`

---

## Ściąga komend

### Instalacja i system (CachyOS / Arch)
| Komenda | Do czego |
| --- | --- |
| `sudo pacman -S --needed <pakiety>` | Instalacja pakietów; `--needed` pomija już zainstalowane |
| `pacman -Q <pakiet>` | Czy pakiet jest zainstalowany i w jakiej wersji |
| `pacman -Ss <fraza>` | Wyszukiwanie pakietu w repozytoriach |
| `pacman -F <plik.so>` | Który pakiet dostarcza dany plik (po `sudo pacman -Fy`) |
| `archlinux-java status` | Lista JDK i który jest domyślny |
| `sudo archlinux-java set java-21-openjdk` | Ustawienie domyślnej Javy |
| `sudo systemctl enable --now docker` | Włącz usługę przy starcie i uruchom od razu |
| `systemctl is-active docker` / `is-enabled` | Czy usługa działa / czy startuje z systemem |
| `sudo usermod -aG docker $USER` | Dodanie użytkownika do grupy (`-a` = dopisz, nie zastępuj) |
| `getent group docker` | Kto jest zapisany w grupie (w systemie) |
| `id -nG` | Grupy aktywne w bieżącej sesji |
| `newgrp docker` | Nowa powłoka z aktywną grupą (tylko to okno) |

### fish – zmienne środowiskowe
| Komenda | Do czego |
| --- | --- |
| `set -x ZMIENNA wartość` | Zmienna eksportowana na czas sesji terminala |
| `set -Ux ZMIENNA wartość` | Zmienna eksportowana na stałe (zapis w `~/.config/fish/fish_variables`) |
| `set -u ZMIENNA wartość` | Zdjęcie eksportu z istniejącej zmiennej |
| `set -S ZMIENNA` | Zakres (global/universal) i czy jest eksportowana |
| `set -e ZMIENNA` / `set -eU ZMIENNA` | Usunięcie zmiennej (sesyjnej / stałej) |
| `env \| grep ZMIENNA` | Czy zmienną widzą programy uruchamiane z terminala |
| `ZMIENNA=x komenda` | Zmienna tylko dla jednej komendy (fish 3.1+) |

### Docker
| Komenda | Do czego |
| --- | --- |
| `docker run -d --name restful-booker -p 3001:3001 mwinteringham/restfulbooker` | Pierwsze uruchomienie API lokalnie |
| `docker start restful-booker` / `docker stop restful-booker` | Uruchomienie / zatrzymanie istniejącego kontenera |
| `docker ps` / `docker ps -a` | Działające / wszystkie kontenery |
| `docker rm -f <id lub nazwa>` | Usunięcie kontenera (także działającego) |
| `docker run --rm hello-world` | Test, czy Docker działa bez `sudo` |

### API (curl)
| Komenda | Do czego |
| --- | --- |
| `curl -i http://localhost:3001/ping` | Odpowiedź z nagłówkami (`-i`); oczekiwane `201 Created` |
| `curl -s http://localhost:3001/booking` | Lista rezerwacji (`-s` = bez paska postępu) |
| `curl -s -X POST http://localhost:3001/auth -H "Content-Type: application/json" -d "{\"username\":\"admin\",\"password\":\"$API_PASSWORD\"}"` | Pobranie tokenu |

### Git
| Komenda | Do czego |
| --- | --- |
| `git status` | Co się zmieniło i co czeka na commit |
| `git log --oneline` | Historia w skrócie (jeden commit = jedna linia) |
| `git log --oneline --graph --all` | Drzewo gałęzi |
| `git log --stat` / `git log -p -1` | Zmienione pliki / pełny diff ostatniego commita |
| `git ls-files` | Pliki śledzone przez Git |
| `git restore --staged <plik>` | Cofnięcie `git add` (pliku nie było jeszcze w commicie) |
| `git rm --cached <plik>` | Przestań śledzić plik, który już jest w repo (zostaje na dysku) |
| `git check-ignore -v <plik>` | Która reguła `.gitignore` ignoruje plik |
| `git mv <z> <do>` | Przeniesienie pliku z zapisem w Gicie |
| `git branch` / `git branch -a` | Gałęzie lokalne / lokalne i zdalne |
| `git branch -M main` | Zmiana nazwy bieżącej gałęzi na `main` |
| `git switch -c <gałąź>` | Utworzenie nowej gałęzi i przejście na nią (starszy zapis: `git checkout -b`) |
| `git switch main` | Przejście na istniejącą gałąź |
| `git branch -d <gałąź>` | Usunięcie scalonej gałęzi lokalnej |
| `git push -u origin main` | Wypchnięcie i ustawienie śledzenia gałęzi zdalnej (potem wystarczy `git push`) |
| `git remote -v` | Lista repozytoriów zdalnych i ich adresów |
| `git remote add origin <url>` | Dodanie repozytorium zdalnego |
| `git remote rename <stara> <nowa>` | Zmiana nazwy repozytorium zdalnego |
| `git push origin --delete <gałąź>` | Usunięcie gałęzi na serwerze |
| `git fetch` | Pobranie zmian z serwera bez scalania |
| `git pull` | `fetch` + scalenie z bieżącą gałęzią |
| `git fetch --prune` | Usunięcie lokalnych odnośników do gałęzi skasowanych na serwerze |
| `git remote set-head origin -a` | Ustawienie `origin/HEAD` na domyślną gałąź serwera |
| `git config --global user.name/user.email` | Autor commitów |

### GitHub CLI (`gh`)
| Komenda | Do czego |
| --- | --- |
| `sudo pacman -S github-cli` | Instalacja |
| `gh auth login` | Logowanie przez przeglądarkę (też dla Gita w terminalu) |
| `gh auth status` | Czy i jako kto jestem zalogowany |
| `gh repo view --web` | Otwarcie repozytorium w przeglądarce |
| `gh repo edit --default-branch main` | Zmiana domyślnej gałęzi |
| `gh pr create` / `gh pr list` | Utworzenie / lista Pull Requestów |

### IntelliJ
| Skrót / miejsce | Do czego |
| --- | --- |
| `Ctrl+Alt+L` | Formatowanie pliku |
| `Ctrl+Alt+O` | Usunięcie nieużywanych importów |
| `Alt+6` (Problems) | Lista ostrzeżeń i błędów w pliku |
| Code → Inspect Code | Analiza kodu dla wybranego zakresu |
| `Ctrl+Shift+K` | Push |
| `Ctrl+Shift+F10` | Uruchomienie testu/klasy pod kursorem |
| Refactor → Move Class (`F6`) | Przeniesienie klasy z poprawą pakietu i importów |

### Maven
| Komenda | Do czego |
| --- | --- |
| `mvn -v` | Wersja Mavena i **której Javy używa** |
| `mvn clean test` | Wyczyść `target/` i uruchom testy |
| `mvn clean verify` | Testy + pakowanie do JAR + sprawdzenia |
| `mvn test -Dgroups=smoke` | Tylko testy z danym tagiem |
| `mvn dependency:tree -Dincludes=org.junit*` | Drzewo zależności, wykrywanie konfliktów wersji |

---

## Słowniczek

| Pojęcie | Wyjaśnienie |
| --- | --- |
| GAV | groupId + artifactId + version – jednoznaczny identyfikator projektu i każdej biblioteki w Mavenie |
| SNAPSHOT | Wersja w trakcie rozwoju; wersja bez dopisku jest wydana i niezmienna |
| Zależność (dependency) | Biblioteka, która trafia do kodu (np. JUnit, AssertJ) |
| Plugin | Narzędzie wykonujące pracę przy budowaniu (kompilator, Surefire); nie trafia do kodu |
| `dependencyManagement` | Katalog wersji – ustala wersje, ale niczego nie dodaje do projektu |
| BOM | Gotowy katalog wersji od twórców biblioteki (np. `junit-bom`), importowany przez `scope import` |
| Scope `test` | Biblioteka dostępna tylko w `src/test/java` |
| `src/main/java` vs `src/test/java` | Kod frameworka vs testy; kompilowane w różnych fazach (`compile` / `test-compile`) |
| Surefire | Plugin uruchamiający testy; domyślnie klasy `*Test`, `*Tests`, `Test*`, `*TestCase` |
| Archetyp | Szablon projektu Mavena (`maven-archetype-archetype` to szablon do tworzenia szablonów – nie do zwykłych projektów) |
| Zmienna środowiskowa | Para NAZWA=wartość przekazywana programom; program dziedziczy środowisko od procesu, który go uruchomił |
| Zmienna eksportowana | Zmienna widoczna dla programów uruchamianych z terminala (`-x` w fish, `export` w bash) |
| Kontener / obraz | Obraz = szablon (np. `mwinteringham/restfulbooker`); kontener = uruchomiona instancja obrazu |
| Mapowanie portów `-p 3001:3001` | `port_hosta:port_kontenera` |
| Staging (poczekalnia) | Pliki dodane przez `git add`, czekające na commit |
| Guard clause | Walidacja na początku metody, która od razu przerywa działanie przy złych danych |
| Immutable | Obiekt, którego nie można zmienić; metody zwracają nowy obiekt (np. `BigDecimal`, `String`) |
| CLI | Command Line Interface – program obsługiwany komendami tekstowymi w terminalu (np. `git`, `mvn`, `docker`, `gh`) |
| Remote | Repozytorium zdalne (np. na GitHubie), z którym synchronizuję lokalne repo |
| `origin` | Konwencjonalna nazwa głównego remote'a; tylko nazwa, nie słowo kluczowe |
| Upstream (gałąź śledzona) | Gałąź zdalna powiązana z lokalną (`-u`); dzięki niej `git push`/`git pull` nie wymagają argumentów |
| `HEAD` | Wskaźnik na commit/gałąź, na której obecnie jestem |
| `origin/HEAD` | Domyślna gałąź repozytorium zdalnego |
| Gałąź domyślna | Gałąź, którą widać po wejściu w repo i do której domyślnie kierowane są PR; nie można jej usunąć |
| Personal Access Token (PAT) | Hasło-token do API i Gita przez HTTPS, z ograniczonymi uprawnieniami i datą ważności |
| Klucz SSH | Para kluczy (prywatny u mnie, publiczny na serwerze) do uwierzytelniania bez hasła |
| `~/.m2/repository` | Lokalny cache bibliotek pobranych przez Mavena |
| Conventional Commits | Konwencja opisów commitów: `typ: opis` (`feat`, `fix`, `test`, `refactor`, `docs`, `chore`, `ci`) |
| Klasa / obiekt | Klasa to szablon (np. `Booking`); obiekt to konkretna instancja utworzona przez `new` |
| Konstruktor | Specjalna metoda wywoływana przy `new`; ustawia pola i może walidować dane |
| Enkapsulacja | Ukrywanie stanu obiektu (pola `private`) i udostępnianie go tylko przez metody, które pilnują poprawności |
| Dziedziczenie | Klasa przejmuje pola i metody innej klasy (`extends`); w Javie tylko jedna klasa bazowa |
| Polimorfizm | Jeden typ (np. `Authenticator`), wiele implementacji; kod działa na typie, nie na konkretnej klasie |
| Abstrakcja | Pokazywanie „co” robi obiekt, ukrywanie „jak” (interfejsy, klasy abstrakcyjne) |
| Interfejs | Kontrakt: lista metod do zaimplementowania (`implements`); klasa może implementować wiele interfejsów |
| Klasa abstrakcyjna | Klasa, której nie można utworzyć przez `new`; może mieć pola, konstruktor i gotowe metody (`extends`) |
| `@Override` | Adnotacja: metoda nadpisuje/implementuje metodę z typu nadrzędnego; kompilator sprawdza poprawność |
| Kompozycja | Obiekt korzysta z innych obiektów przez pola („ma”), zamiast po nich dziedziczyć („jest”) |
| Wstrzykiwanie zależności (DI) | Klasa dostaje potrzebne obiekty/dane z zewnątrz (np. przez konstruktor), zamiast tworzyć je sama |
| Programowanie do interfejsu | Zmienne i parametry mają typ interfejsu (`Authenticator a = new InMemoryAuthenticator(...)`) |
| `final` (pole) | Pole można przypisać tylko raz (w konstruktorze) |
| `static` | Element należy do klasy, nie do obiektu (np. stałe `private static final`) |
| Fake (atrapa) | Uproszczona, działająca implementacja do testów (np. `InMemoryAuthenticator` zamiast prawdziwego API) |
| UUID | Losowy 128-bitowy identyfikator, praktycznie unikalny; `UUID.randomUUID()` |
| Off-by-one | Błąd o jeden (np. liczenie dni zamiast nocy); wykrywają go testy wartości brzegowych |
| `LocalDate` / `ChronoUnit.DAYS.between` | Data bez czasu i strefy / liczba dni między datami (koniec nie wliczony) |

---

## Decyzje projektowe

| Decyzja | Alternatywy | Uzasadnienie |
| --- | --- | --- |
| JUnit 6 (Jupiter) | TestNG | Częściej wybierany w nowych projektach, oficjalna integracja z Playwright for Java; API Jupiter jak w JUnit 5 |
| `junit-bom` w `dependencyManagement` | Wersja przy każdej zależności | Jedna spójna wersja wszystkich modułów JUnit, także pośrednich (Allure, Testcontainers) |
| Jawne wersje pluginów | Wersje domyślne Mavena | Projekt zachowuje się tak samo na każdym komputerze i w CI |
| `BigDecimal` dla kwot | `double`, `long` w groszach | `double` jest niedokładny przy ułamkach dziesiętnych |
| Hasło API w zmiennej środowiskowej | Hasło w kodzie / pliku | Sekret nie trafia do repozytorium; ten sam kod działa lokalnie i w CI |
| Restful Booker w Dockerze | Publiczna instancja | Niezależność od dostępności internetu i resetów publicznego serwera |
| Walidacja w konstruktorze `Booking` | Osobna metoda `validate()`, walidacja w setterach | Niepoprawny obiekt nie może powstać (fail fast); brak setterów = obiekt niezmienny |
| Mapa użytkowników przez konstruktor | Mapa na sztywno w klasie | Testy podają własne dane (dependency injection) |
| Wspólny komunikat dla złego loginu i hasła | Osobne komunikaty | Utrudnia sprawdzanie, które loginy istnieją |
| `BaseTest` jako klasa przejściowa | Od razu extension | Ćwiczenie dziedziczenia; w sesji 8 zamiana na extension (kompozycja) |
| Praca na gałęziach i PR od sesji 2 | Commity prosto do `main` | Nawyk z pracy zespołowej, przygotowanie do branch policies w sesji 33 |

---

## Sesje

### Sesja 0 – Przygotowanie środowiska (2026-10-05)

**Czego się nauczyłem**
- CachyOS bazuje na Arch – pakiety instaluję przez `pacman`, a kilka JDK może współistnieć; domyślny wybieram przez `archlinux-java`.
- Najważniejszy test środowiska Javy to `mvn -v` – pokazuje, której Javy faktycznie używa Maven.
- Docker działa jako usługa systemowa (`systemctl`). Żeby używać go bez `sudo`, użytkownik musi być w grupie `docker`, a grupy wczytują się dopiero przy logowaniu.
- Zmienna środowiskowa trafia tylko do programów uruchomionych z procesu, w którym ją ustawiono. Zmienna zwykła (`set`) jest widoczna tylko w powłoce; eksportowana (`set -x`) – także dla programów.
- IntelliJ uruchomiony z menu aplikacji nie widzi zmiennych z fish – trzeba je ustawić w konfiguracji uruchomienia (Run → Edit Configurations) albo w `~/.config/environment.d/`.
- `curl -i` pokazuje pełną odpowiedź HTTP: kod statusu, nagłówki i treść.

**Problemy i jak je rozwiązałem**
- `permission denied ... docker.sock` – `getent group docker` pokazywał mnie w grupie, ale `id -nG` nie. Sesja była sprzed `usermod`; rozwiązanie: restart komputera (wylogowanie przy logowaniu bez hasła nie zawsze odświeża grupy).
- Zmienna po `set API_PASSWORD ...` była widoczna w `env`, bo istniała już wcześniej jako eksportowana. W fish `set` bez flag zachowuje właściwości istniejącej zmiennej.
- Playwright: flaga `--with-deps` używa `apt`, którego nie ma w Arch – instaluję przeglądarki bez niej; WebKit w razie problemów uruchamiam w kontenerze `mcr.microsoft.com/playwright/java`.

**Obserwacje z API Restful Booker**
- `/ping` zwraca `201 Created` zamiast typowego `200 OK` – do zgłoszenia jako uwaga (sesja 9).
- Nagłówek `X-Powered-By: Express` ujawnia technologię serwera (Node.js) – drobna uwaga bezpieczeństwa.
- `Content-Type: text/plain` – `/ping` nie zwraca JSON.

**Pytania rekrutacyjne**

**P: Czym jest zmienna środowiskowa i dlaczego trzyma się w niej hasła zamiast w kodzie?**
O: To wartość przekazywana procesowi przez system i dziedziczona przez procesy potomne. Kod tylko odczytuje wartość (`System.getenv`), a samą wartość ustawia się na zewnątrz: lokalnie w terminalu, w CI jako sekret. Dzięki temu hasło nie trafia do repozytorium, którego historia jest trwała i dostępna dla wielu osób.

**P: Czym różni się obraz Dockera od kontenera?**
O: Obraz to niezmienny szablon (system + aplikacja). Kontener to uruchomiona instancja obrazu z własnym stanem. Z jednego obrazu można uruchomić wiele kontenerów.

**P: Co oznacza `-p 3001:3001` w `docker run`?**
O: Mapowanie portu: port 3001 na moim komputerze jest przekierowany do portu 3001 w kontenerze (`host:kontener`).

**P: Co zawiera odpowiedź HTTP?**
O: Linię statusu (wersja protokołu, kod, opis), nagłówki (np. `Content-Type`, `Content-Length`) i opcjonalnie treść (body).

---

### Sesja 1 – Środowisko Maven i pierwszy test (2026-10-06)

**Czego się nauczyłem**
- Struktura `pom.xml`: nagłówek, współrzędne GAV, `properties`, `dependencyManagement`, `dependencies`, `build/plugins`.
- `maven.compiler.release` ustala wersję Javy dla kompilatora; bez niej kompilator 3.14 domyślnie celuje w Javę 8.
- `project.build.sourceEncoding=UTF-8` – bez tego ostrzeżenie i ryzyko zepsutych polskich znaków.
- `junit-jupiter-api` to same adnotacje i asercje; do uruchomienia testów potrzebny jest silnik – dlatego używam artefaktu `junit-jupiter`.
- Fazy Mavena: `clean` → `compile` → `test-compile` → `test` → `package` → `verify`; do każdej fazy przypięty jest plugin.
- Plik `.gitignore` działa tylko w katalogu, w którym leży, i w podkatalogach; jedna reguła = jedna linia.
- `.gitignore` nie działa na pliki już śledzone przez Git.
- Struktura pakietów testów odzwierciedla strukturę kodu: `src/test/java/com/bookingqa/utils` dla `src/main/java/com/bookingqa/utils`.
- `BigDecimal`: tworzę z tekstu (`new BigDecimal("0.90")`), działam metodami (`multiply`, `add`), wynik przypisuję (obiekt jest niezmienny), porównuję przez `compareTo` / `isEqualByComparingTo`.
- Soft asercje mają sens przy kilku asercjach w jednym teście; przy jednej asercji wystarczy zwykłe `assertThat`.
- Konwencja nazw testów: `shouldCalculateRegularPrice`; od JUnit 5 klasy i metody testowe nie muszą być `public`.
- Konwencja commitów (Conventional Commits): `feat:`, `fix:`, `test:`, `refactor:`, `docs:`, `chore:`.

**Problemy i jak je rozwiązałem**
- Projekt utworzony z `maven-archetype-archetype` – pojawiły się pliki `archetype-resources/` i `META-INF/maven/archetype.xml`. Usunąłem je z Gita i z dysku.
- `maven-surefire-plugin` wpisany jako `<dependency>` – przeniosłem do `<build><plugins>`.
- Reguły wpisane w `.idea/.gitignore` i w jednej linii z przecinkami (`target/, .idea/, *.iml`) – przeniosłem do głównego `.gitignore`, każdą w osobnej linii.
- Pliki `.idea/` w poczekalni mimo `.gitignore` – `git rm --cached` odmówił (plik miał zmiany staged i niestaged), pomogło `git restore --staged .idea`.
- Test w pakiecie domyślnym, potem w `src/main/java` (nie kompilował się, bo JUnit ma scope `test`), potem w pakiecie `utils` – docelowo `src/test/java/com/bookingqa/utils`.
- Zmiana typu na `BigDecimal` – błąd konwersji, bo `BigDecimal` to obiekt bez operatorów `*`, `+`.
- IntelliJ zgłaszał ostrzeżenie przed commitem, którego nie dało się otworzyć – można je znaleźć w oknie Problems (`Alt+6`) lub przez Code → Inspect Code.
- `git push -u origin main` → „'origin' does not appear to be a git repository”. IntelliJ przy publikacji nazwał remote `booking-qa-framework`. Rozwiązanie: `git remote rename booking-qa-framework origin`.
- Terminal pytał o hasło do GitHuba, a loguję się przez Google. GitHub nie przyjmuje haseł dla Gita przez HTTPS – zalogowałem się przez `gh auth login` (przeglądarka + jednorazowy kod).
- `git push origin --delete master` → „refusing to delete the current branch”. Gałęzi domyślnej nie można usunąć – najpierw zmiana domyślnej na `main` (`gh repo edit --default-branch main`), potem usunięcie i `git fetch --prune`.

**Weryfikacja końcowa sesji 1**
- `mvn clean verify` → `Tests run: 4, Failures: 0`, `BUILD SUCCESS`
- `git ls-files` → tylko `.gitignore`, `NOTES.md`, `pom.xml`, `src/...`
- `git branch -a` → `main`, `origin/HEAD -> origin/main`, `origin/main`

**Do poprawy na początku sesji 2**
- [ ] Formatowanie (`Ctrl+Alt+L`) w `PriceCalculator` i teście
- [ ] Test dla ujemnej liczby nocy (`-1`) i sprawdzenie komunikatu wyjątku (`hasMessageContaining`)
- [ ] Pole `calculator` w klasie testowej zamiast `new PriceCalculator()` w każdym teście

**Pytania rekrutacyjne**

**P: Czym różni się `dependencyManagement` od `dependencies`?**
O: `dependencyManagement` tylko ustala wersje bibliotek (katalog wersji), niczego nie dodając do projektu. `dependencies` faktycznie dodaje biblioteki. Dzięki temu wersje są ustalone w jednym miejscu – ważne przy zależnościach pośrednich i w projektach wielomodułowych.

**P: Czym różni się zależność od pluginu w Mavenie?**
O: Zależność to biblioteka używana w kodzie. Plugin to narzędzie, które wykonuje pracę przy budowaniu (kompilacja, uruchamianie testów, pakowanie) i nie trafia do wyniku.

**P: Czym różni się `mvn test` od `mvn verify`?**
O: `test` kończy się po uruchomieniu testów jednostkowych. `verify` idzie dalej: pakuje projekt (`package`) i wykonuje dodatkowe sprawdzenia, np. testy integracyjne czy narzędzia jakości kodu.

**P: Jakie znasz zakresy (scope) zależności?**
O: `compile` (domyślny, wszędzie), `test` (tylko testy), `provided` (kompilacja, ale nie w wyniku – np. Lombok), `runtime` (tylko przy uruchomieniu – np. sterownik bazy), `import` (tylko dla BOM w `dependencyManagement`).

**P: Dlaczego nie przechowuje się kwot w `double`?**
O: `double` zapisuje liczby binarnie, więc wielu ułamków dziesiętnych (np. 0.1) nie da się zapisać dokładnie – wyniki typu `0.30000000000000004`. Do pieniędzy używa się `BigDecimal` albo liczb całkowitych w groszach.

**P: Dlaczego `new BigDecimal("0.9")`, a nie `new BigDecimal(0.9)`?**
O: Konstruktor z `double` przenosi niedokładność `double` (`0.9000000000000000222...`). Konstruktor z `String` (lub `BigDecimal.valueOf`) daje dokładną wartość.

**P: Czym różni się `equals()` od `compareTo()` w `BigDecimal`?**
O: `equals()` porównuje wartość i skalę (liczbę miejsc po przecinku), więc `500.00` nie równa się `500`. `compareTo()` porównuje tylko wartość. W AssertJ odpowiednikiem jest `isEqualByComparingTo`.

**P: Kiedy użyć soft asercji?**
O: Gdy w jednym teście sprawdzam kilka niezależnych warunków i chcę zobaczyć wszystkie błędy naraz, a nie tylko pierwszy (np. kilka pól obiektu w odpowiedzi API). Przy jednej asercji nic nie wnoszą.

**P: Dlaczego plik dodany do `.gitignore` nadal pojawia się w `git status`?**
O: Bo był już śledzony (dodany przez `git add` lub zacommitowany). `.gitignore` działa tylko na pliki nieśledzone. Rozwiązanie: `git restore --staged` (jeśli nie był w commicie) albo `git rm --cached` (jeśli był).

**P: Czym różni się `git fetch` od `git pull`?**
O: `fetch` tylko pobiera zmiany z serwera i aktualizuje gałęzie zdalne (`origin/main`), nie ruszając mojej pracy. `pull` to `fetch` + scalenie (merge lub rebase) z bieżącą gałęzią. `fetch` jest bezpieczny – najpierw sprawdzam, co przyszło, potem decyduję.

**P: Co robi `-u` w `git push -u origin main`?**
O: Ustawia gałąź śledzoną (upstream): lokalny `main` jest powiązany z `origin/main`. Potem `git push`, `git pull` i `git status` (ahead/behind) działają bez podawania nazw.

**P: Jak uwierzytelnić się w GitHubie z terminala?**
O: Nie hasłem. Opcje: Personal Access Token przez HTTPS, klucz SSH albo GitHub CLI (`gh auth login`), który konfiguruje Gita automatycznie. W firmach najczęściej SSH lub logowanie przez SSO.

**P: Ile instancji klasy testowej tworzy JUnit?**
O: Domyślnie nową dla każdej metody testowej (`@TestInstance(Lifecycle.PER_METHOD)`). Dzięki temu pola klasy nie przenoszą stanu między testami i testy są od siebie niezależne. Można to zmienić na `PER_CLASS`.

---

### Wiedza dodatkowa po sesji 1

Rzeczy, które nie padły wprost w zadaniach, ale warto je znać po tym etapie.

**Maven**
- Biblioteki pobrane przez Mavena trafiają do `~/.m2/repository` i są współdzielone przez wszystkie projekty. Usunięcie tego katalogu wymusza ponowne pobranie (pomocne przy uszkodzonych plikach).
- `mvn -o` (offline) buduje bez internetu z lokalnego cache.
- `mvn test -Dtest=PriceCalculatorTest` uruchamia jedną klasę; `-Dtest=PriceCalculatorTest#shouldCalculateDiscount` – jedną metodę.
- Raporty Surefire są w `target/surefire-reports/` (pliki `.txt` i `.xml`). Plik XML w sesji 34 trafi do Azure DevOps.
- Maven Wrapper (`mvnw`, katalog `.mvn/`) pozwala uruchomić projekt bez instalowania Mavena, w ustalonej wersji. Popularny w projektach zespołowych i w CI.
- Wersjonowanie semantyczne (SemVer): `MAJOR.MINOR.PATCH` – zmiana MAJOR (np. JUnit 5 → 6) może łamać kompatybilność, MINOR dodaje funkcje, PATCH naprawia błędy.

**JUnit 6 i AssertJ**
- Cykl życia testu: `@BeforeAll` (raz, metoda statyczna) → dla każdego testu: nowa instancja klasy → `@BeforeEach` → `@Test` → `@AfterEach` → na końcu `@AfterAll`.
- Dobry test ma strukturę AAA: Arrange (przygotowanie), Act (wywołanie), Assert (sprawdzenie).
- Jeden test sprawdza jedno zachowanie; nazwa mówi, co ma się stać (`shouldRejectZeroNights`).
- AssertJ: `assertThat(x)` zwraca asercje dopasowane do typu – dla `BigDecimal` jest `isEqualByComparingTo`, dla list `containsExactly`, dla wyjątków `assertThatThrownBy(...).hasMessageContaining(...)`.
- Wartości graniczne do testowania przy regule „powyżej 7 nocy rabat, ≤ 0 błąd”: -1, 0, 1, 7, 8. To technika analizy wartości brzegowych (boundary value analysis).

**Git**
- Commit = zapis stanu wszystkich śledzonych plików + autor + data + opis + wskaźnik na poprzedni commit.
- Trzy obszary: katalog roboczy (pliki na dysku) → poczekalnia (`git add`) → repozytorium (`git commit`).
- Nie zmieniam historii (`--amend`, `rebase`, `push --force`) na gałęziach, które ktoś inny mógł już pobrać.
- `git diff` pokazuje zmiany niedodane do poczekalni, `git diff --staged` – dodane.
- Przed commitem: `git status` + `git diff --staged`, żeby wiedzieć, co dokładnie zatwierdzam.

**Bezpieczeństwo**
- Sekretów nie da się „usunąć” z repozytorium zwykłym commitem – zostają w historii. Jedyne pewne rozwiązanie po wycieku: unieważnić (zmienić) sekret.
- Narzędzia do skanowania sekretów: `gitleaks`, GitHub secret scanning (sesja 28).

**CLI – czym jest**
- CLI (Command Line Interface) to sposób obsługi programu przez wpisywanie komend tekstowych, w przeciwieństwie do GUI (okna, przyciski).
- Budowa komendy: `program podkomenda --opcja argument`, np. `git push -u origin main`, `gh repo edit --default-branch main`.
- Zalety CLI w automatyzacji: komendy da się zapisać w skrypcie i powtórzyć identycznie, działają na serwerach bez ekranu i w pipeline CI/CD. Dlatego pipeline w Azure DevOps (sesja 34) to w praktyce lista komend CLI (`mvn ...`, `git ...`).
- Pomoc: `<program> --help` lub `man <program>`, np. `gh pr create --help`.
- GitHub CLI (`gh`) to oficjalne narzędzie GitHuba do obsługi tego, co normalnie robię na stronie (repozytoria, PR, issues, ustawienia). Git zarządza historią kodu; `gh` zarządza platformą GitHub. Odpowiednikiem dla Azure DevOps jest `az devops` (Azure CLI).

---

### Sesja 2 – OOP w praktyce (2026-10-08)

**Co powinienem umieć po tej sesji**
- [ ] Wyjaśnić 4 filary OOP (enkapsulacja, dziedziczenie, polimorfizm, abstrakcja) na przykładach z projektu
- [ ] Napisać klasę z prywatnymi polami `final`, konstruktorem i walidacją
- [ ] Napisać interfejs i jego implementację z `@Override`
- [ ] Wyjaśnić, kiedy interfejs, a kiedy klasa abstrakcyjna
- [ ] Przekazać zależność przez konstruktor (dependency injection) zamiast tworzyć ją w środku
- [ ] Zadeklarować zmienną typem interfejsu (`Authenticator a = new InMemoryAuthenticator(...)`)
- [ ] Napisać testy walidacji (`assertThatThrownBy`) i testy wartości (`assertThat(...).isEqualTo`)
- [ ] Dobrać przypadki testowe: zwykły, brzegowy, negatywny, `null`
- [ ] Pisać testy niezależne od daty uruchomienia (stałe daty zamiast `LocalDate.now()`)
- [ ] Pracować na gałęzi i scalać przez Pull Request

**Czego się nauczyłem**
- Model `Booking` z enkapsulacją: pola `private final`, stan ustawiany tylko w konstruktorze, obiekt niezmienny po utworzeniu.
- Walidacja w konstruktorze gwarantuje, że niepoprawny obiekt w ogóle nie powstanie – metody (np. `nights()`) nie muszą już sprawdzać danych.
- `ChronoUnit.DAYS.between(checkIn, checkOut)` liczy noce (dzień wyjazdu nie jest wliczany).
- Interfejs `Authenticator` (kontrakt „co”) i implementacja `InMemoryAuthenticator` (konkret „jak”) – polimorfizm.
- Mapa użytkowników jest polem i trafia do klasy przez konstruktor (dependency injection) – w testach mogę podać dowolnych użytkowników.
- `UUID.randomUUID()` generuje losowy, praktycznie unikalny token.
- `BaseTest` z `@BeforeEach` i `TestInfo` – JUnit wstrzykuje informacje o teście jako parametr metody. To klasa przejściowa – w sesji 8 zastąpi ją extension.
- Pracowałem na gałęzi `sesja-2` i scaliłem ją do `main` przez Pull Request.

**Problemy i jak je rozwiązałem**
- Nie wiedziałem, gdzie umieścić mapę użytkowników. Mapa to stan obiektu, więc jest polem; metoda tylko ją czyta.
- Test „liczba nocy > 0” był za słaby – przechodził nawet przy złym wyniku. Zastąpiłem go testem dokładnej wartości i testem brzegowym (1 noc).

**Wnioski z code review (do poprawy na początku sesji 3)**
- [x] `InMemoryAuthenticator`: warunek `!userName.equals(users.keySet())` porównuje `String` ze zbiorem `Set` – zawsze `false`, więc ta część warunku nic nie robi. Kod działa przypadkiem. Poprawnie: `!users.containsKey(userName) || !users.get(userName).equals(userPassword)`.
- [ ] `InMemoryAuthenticator`: `null` jako login daje `NullPointerException` (mapy z `Map.of` nie akceptują `null` nawet w `containsKey`). `null` jako hasło działa poprawnie (`IllegalArgumentException`). Dodać obsługę `null` loginu (sesja 5).
- [x] `Booking`: `checkIn.isAfter(checkOut)` przepuszcza rezerwację z tą samą datą przyjazdu i wyjazdu (0 nocy). Poprawnie: `!checkOut.isAfter(checkIn)`.
- [x] `Booking`: walidacja przeniesiona na początek konstruktora (guard clause), `isBlank()` zamiast `isEmpty()`.
- [x] `Booking`: jeden komunikat dla wszystkich błędów – osobny komunikat dla każdego warunku mówi, co jest nie tak.
- [x] Testy: `LocalDate.now()` sprawia, że wynik zależy od dnia uruchomienia – stałe daty w przyszłości.
- [x] Testy: nazwa `shouldThrowExceptionWhenNight0OrLess` sprawdza cenę 0, a nie noce; `shouldThrowExceptionWhenCheckoutAfterCheckIn` powinna brzmieć `...WhenCheckoutBeforeCheckIn`.
- [x] Dopisane testy: złe hasło dla istniejącego użytkownika, checkin = checkout.
- [ ] Brakujący test: dwa logowania dają różne tokeny.
- [ ] Testy wyjątków nie sprawdzają komunikatu (`hasMessageContaining`) – nie wiadomo, który warunek rzucił wyjątek.
- [x] Drobne: usunięte `public` w interfejsie, poprawione formatowanie.
- [ ] Drobne: `public` przy klasach testowych `BookingTest` i `InMemoryAuthenticatorTest`.
- [x] Git: usunięta scalona gałąź `sesja-2`.
- [ ] Git: typy commitów `added:`/`fixed:` – poprawne typy to `feat:`, `test:`, `fix:`.
- [ ] `Booking`: `null` w imieniu lub datach daje `NullPointerException` – dodać `Objects.requireNonNull` (do zrobienia w sesji 5 razem z wyjątkami).
- [ ] Testy: `shouldNotGetToken` ma 3 asercje dla 3 różnych przypadków – rozdzielić na osobne testy lub `@ParameterizedTest` (sesja 8).

**Pytania rekrutacyjne**

**P: Wymień cztery filary OOP.**
O: Enkapsulacja – ukrywanie stanu za metodami (`private` pola w `Booking`). Dziedziczenie – przejmowanie zachowania klasy nadrzędnej (`PriceCalculatorTest extends BaseTest`). Polimorfizm – jeden typ, wiele implementacji (`Authenticator` i `InMemoryAuthenticator`). Abstrakcja – pokazanie „co” bez „jak” (interfejs `Authenticator`).

**P: Interfejs czy klasa abstrakcyjna – kiedy którego używam?**
O: Interfejs, gdy definiuję kontrakt, który mogą spełniać niezwiązane klasy; klasa może implementować wiele interfejsów. Klasa abstrakcyjna, gdy klasy są blisko spokrewnione i chcę współdzielić stan (pola) i gotowy kod; można dziedziczyć tylko po jednej. Od Javy 8 interfejsy mogą mieć metody `default`, ale nadal nie mają stanu (pól instancji) ani konstruktora.

**P: Czym jest enkapsulacja i po co ją stosować?**
O: Pola są prywatne, a dostęp do nich jest kontrolowany przez konstruktor i metody. Dzięki temu obiekt pilnuje swojej poprawności – np. `Booking` nie pozwoli ustawić ujemnej ceny, bo nikt nie zmieni pola z zewnątrz.

**P: Czym jest dependency injection i dlaczego ułatwia testowanie?**
O: Klasa dostaje zależności z zewnątrz (np. przez konstruktor), zamiast tworzyć je sama. W testach mogę podać atrapę lub własne dane – `InMemoryAuthenticator` dostaje mapę użytkowników, więc każdy test może mieć innych użytkowników.

**P: Dlaczego walidować dane w konstruktorze?**
O: Żeby niepoprawny obiekt nigdy nie powstał (fail fast). Błąd pojawia się w miejscu, gdzie powstał, a nie później w zupełnie innej części kodu.

**P: Kompozycja czy dziedziczenie?**
O: Domyślnie kompozycja („ma”): jest elastyczniejsza, a klasa może składać wiele zachowań. Dziedziczenie („jest”) wiąże klasy na stałe i w Javie pozwala tylko na jedną klasę bazową. W JUnit dlatego zamiast `BaseTest` stosuje się extensions (`@ExtendWith`), które można łączyć.

**P: Dlaczego komunikat błędu logowania nie powinien mówić, czy zły był login, czy hasło?**
O: Inny komunikat dla nieznanego loginu pozwala atakującemu sprawdzić, które loginy istnieją (user enumeration). Jeden wspólny komunikat utrudnia atak.

**P: Dlaczego `LocalDate.now()` w teście to zły pomysł?**
O: Wynik testu zależy od dnia uruchomienia – test może przechodzić dziś i nie przechodzić jutro (np. przy granicy miesiąca lub gdy data „w przyszłości” stanie się przeszłością). Testy muszą być deterministyczne: te same dane wejściowe zawsze dają ten sam wynik.

---

### Wiedza dodatkowa po sesji 2

**Modyfikatory dostępu**

| Modyfikator | Widoczny w |
| --- | --- |
| `private` | Tylko w tej klasie |
| brak (package-private) | W tym samym pakiecie – dlatego testy w tym samym pakiecie mają dostęp |
| `protected` | W pakiecie i w klasach dziedziczących |
| `public` | Wszędzie |

Zasada: najwęższy możliwy dostęp. Metody w interfejsie są domyślnie `public` i `abstract`, więc nie trzeba tego pisać.

**`final` i `static`**
- `final` przy polu: przypisanie tylko raz. Przy klasie: nie można po niej dziedziczyć (np. `String`). Przy metodzie: nie można jej nadpisać.
- `static`: należy do klasy, a nie do obiektu. Stałe zapisuje się jako `private static final` wielkimi literami (`DISCOUNT_THRESHOLD_NIGHTS`).

**Obsługa `null`**
- `Objects.requireNonNull(value, "firstname must not be null")` – czytelny wyjątek zamiast przypadkowego `NullPointerException`.
- Bezpieczne porównanie: `"stała".equals(zmienna)` lub `Objects.equals(a, b)` zamiast `zmienna.equals(...)`.
- `isBlank()` (od Javy 11) traktuje tekst ze spacjami jako pusty; `isEmpty()` – nie.

**SOLID – zapowiedź (pełny temat w sesji 30)**
- S – Single Responsibility: klasa ma jeden powód do zmiany (`Booking` przechowuje rezerwację, nie loguje użytkowników).
- O – Open/Closed: rozszerzam przez nowe klasy (nowa implementacja `Authenticator`), nie modyfikuję istniejących.
- L – Liskov Substitution: każdą implementację interfejsu da się podstawić bez zmiany zachowania programu.
- I – Interface Segregation: małe, konkretne interfejsy zamiast jednego dużego.
- D – Dependency Inversion: zależę od abstrakcji (`Authenticator`), nie od konkretu (`InMemoryAuthenticator`).

**Projektowanie przypadków testowych**
- Dla każdej reguły walidacji: przypadek poprawny, wartość graniczna, wartość niepoprawna, `null`.
- Każdy warunek walidacji testuję osobno – jeden test łamie jedną regułę, reszta danych jest poprawna. Inaczej nie wiem, który warunek faktycznie zadziałał.
- Asercja wyjątku sprawdza też komunikat (`hasMessageContaining`) – to potwierdza, że wyjątek rzucił właściwy warunek.

**Git – praca na gałęziach**
- Gałąź funkcjonalna (`sesja-2`, `feature/...`) → Pull Request → review → scalenie do `main`.
- Po scaleniu gałąź usuwam (lokalnie `git branch -d sesja-2`, zdalnie `git push origin --delete sesja-2`; na GitHubie przycisk „Delete branch” po scaleniu PR).
- Commit merge („Merge pull request #1”) łączy historie dwóch gałęzi.
