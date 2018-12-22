workflow "Test" {
  on = "push"
  resolves = [
    "JDK8",
    "JDK11",
  ]
}

action "JDK8" {
  uses = "docker://openjdk:8"
  runs = "chmod +x ./gradlew && ./gradlew test"
}

action "JDK11" {
  uses = "docker://openjdk:11"
  runs = "chmod +x ./gradlew && ./gradlew test"
}
