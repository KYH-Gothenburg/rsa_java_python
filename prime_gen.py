def is_prime(n):
    if n < 2:
        return False
    for i in range(2, n):
        if n % i == 0:
            return False

    return True


def main():
    for i in range(1000):
        if is_prime(i):
            print(i, ', ', end='')
    print()


if __name__ == '__main__':
    main()