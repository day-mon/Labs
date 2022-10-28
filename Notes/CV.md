# 1D Discrete Fourier Transform

###  Assume image with just one sinusoid
```python
M = 64
img = np.zeros((M,), dtype=np.complex)


u_omega_1 = 5
omega_1 = (2* np.pi * u_omega_1) / M
amplicitude_1 = 1.0
phi_1 = 2.0                               


for m in range(0, M):
    img[m] = amplicitude_1 * np.exp(1j * (omega_1 * m + phi_1))
```

### Magic: Discrete Fourier Transform
```python
img_freq = np.zeros((M,), dtype=np.complex)
s = complex(0, 0)

for m in range(0, M):
    s += img[m] * (complex(0, -1) * 2 * np.pi * m / M)
```

---

# 2D Discrete Fourier Transform

- The 2D DFT is seperable, which can be seen in this formula:
  - $F(u, v) = \sum_{x=0}^{M-1} \sum_{y=0}^{N-1} f(x, y) e^{-j2\pi \frac{ux}{M}} e^{-j2\pi \frac{vy}{N}}$
- Classical 1D FFt has runtime of O(N log N) for image sizes that are powers of two, which means forming 2D DFT can be very efficient

### 2D DFT in NumpPy code
```python
name_file = 'lena.png'
img = cv2.imread(name_file, cv2.IMREAD_GRAYSCALE)
img = img.astype(np.float32) / 255.0

```
cont..