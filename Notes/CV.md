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

- The 2D DFT is separable, which can be seen in this formula:
  - $F(u, v) = \sum_{x=0}^{M-1} \sum_{y=0}^{N-1} f(x, y) e^{-j2\pi \frac{ux}{M}} e^{-j2\pi \frac{vy}{N}}$
- Classical 1D FFt has runtime of O(N log N) for image sizes that are powers of two, which means forming 2D DFT can be very efficient

### 2D DFT in NumpPy code
```python
name_file = 'lena.png'
img = cv2.imread(name_file, cv2.IMREAD_GRAYSCALE)
img = img.astype(np.float32) / 255.0

```

***
## Chapter 3 Segmentation Thresholding and Otsu's Method

### Otsu's Method - Automatic Thresholding Estimation
- Assumes that the foreground and background are clearly seperated in histogram as two separate mods (hills)
- The threshold is the point where the two hills meet

## Otsus Method - Code (1/3)
- Efficient evaluation for all values u is based ona  histogram
- Equation: $w_0(u) = \sum_{i=0}^{u} p(i)$
```python
# histogram + integral
h = np.zeros((256,), dtype=np.float32)
pixels_total = M * N
integral_total = 0;
for m in range(0, M):
    for n in range(0, N):
        u = img[m, n]
        h[img[m, n]] += 1
        integral_total += u
```
ω σ
⊂
≝


## Edge-based segmentation
- General idea of edge-based segmentation:
1. Find edges
2. Close Edges
3. Fill holes

## Canny Edge Detector - Smoothing
- Classical robust algorithm to find edges, which is named after John F. Canny
1. Noise Removal
   1. Classical version uses gaussian filter and certain σ
   2. The equation: $G(x, y) = \frac{1}{2\pi\sigma^2} e^{-\frac{x^2 + y^2}{2\sigma^2}}$
   3. Based on the σ, ≈ kernel size and integer values
2. Gradient Estimation: Edge magnitude and direction
    1. Estimate gradient vector g = (Gx, Gy) Transposed
    2. Estimate the magnitude r and direction θ of gradient vector (similar to polar for of complex number):
       1. $r = \sqrt{Gx^2 + Gy^2}$
       2. $0 = \tan^{-1}(\frac{Gy}{Gx})$
    3. Round direction to 0, 45, 90, 135 degrees
3. Non-maximum Suppression
   1. Remember: Gradient points in steepest uphill direction
   2. keep in mind: edge itself is orthogonal to gradient 
4. Two thresholds
5. Edge Tracking by Hysteresis

