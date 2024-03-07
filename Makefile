# windows boilerplate
ifeq ($(OS),Windows_NT)
    SHELL := pwsh.exe
else
   SHELL := pwsh
endif
.SHELLFLAGS := -NoProfile -Command


compose:
	docker compose up -d
dist:
	./gradlew clean
test:
	./gradlew test