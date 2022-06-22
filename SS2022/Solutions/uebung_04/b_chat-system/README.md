# Lösungsvorschlag zum "chat-system"

## Testen mit dem Client

Die Klasse `CliClient` stellt einen "command line client" (CLI) zur Verfügung, um sich mit einem Server zu verbinden. 
(Dies ist nicht die Klasse mit der grafischen Benutzeroberfläche, die im Übungsblatt abgebildet ist.)
Diese Klasse kann ohne eine lokale Server-Instanz gestartet werden, um das Server-Protokoll zu testen. 
In diesem Fall verbindet sie sich mit dem zentralen Chat-Server auf dem Rechner `im-vm-011`. 
Hierzu muessen Sie allerdings im WLAN der OTH oder ueber VPN verbunden sein!

**Tipp:** Starten Sie auch einen zweiten Client, um auch das Empfangen von Nachrichten zu pruefen.

## Testen Ihrer Serverimplementierung

Um Ihren lokal gestarteten Server zu testen, müssen in der Klasse `CliClient` den Hostname und ggf. den Port anpassen. Der neue Hostname ist dann `localhost`.

