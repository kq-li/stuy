def origin(messages, index):
    return 0

if __name__ == "__main__":
  with open("MessageOriginsIN.txt", "r") as f:
      while True:
          s = f.readline()
          if s == "":
              break
          data = s.split(" ")
          N = int(data[len(data) - 1])
          messages = [int(x) for x in data[:-1]]
          print origin(messages, N)
