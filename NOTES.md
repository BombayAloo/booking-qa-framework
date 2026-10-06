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
| 2 | OOP w praktyce | | |
| 3 | Kolekcje i generyki | | |

---

## Do powtórki

Odhaczam, gdy umiem wytłumaczyć temat na głos bez notatek.

- [ ] Różnica `equals()` vs `compareTo()` w `BigDecimal`
- [ ] Kiedy `git restore --staged`, a kiedy `git rm --cached`
- [ ] Fazy cyklu życia Mavena po kolei
- [ ] Zmienna zwykła vs eksportowana (fish)

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
| `git branch -M main` | Zmiana nazwy bieżącej gałęzi na `main` |
| `git push -u origin main` | Wypchnięcie i ustawienie śledzenia gałęzi zdalnej |
| `git config --global user.name/user.email` | Autor commitów |

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

---

### Sesja 2 – OOP w praktyce

**Czego się nauczyłem**
- 

**Problemy i jak je rozwiązałem**
- 

**Pytania rekrutacyjne**

**P: Interfejs czy klasa abstrakcyjna – kiedy którego używam?**
O: 
