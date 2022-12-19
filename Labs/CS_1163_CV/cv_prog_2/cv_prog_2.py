# CS 1163 - Advanced Topics - Introduction to Computer Vision
# CV Programming 2 - Convolution and Linear Filters

# import OpenCV, Numerical Python, and Standard Math
import sys
import cv2 as cv
import numpy as np
import math


# clamps a value in the interval [minimum, maximum]
def clamp(value, minimum, maximum):
    return min(maximum, max(minimum, value))


def main():
    print('CV Exercise 2 - Convolution and Linear Filters')
    name_file = 'data/ford_256.png'

    # opening an image, convert to monochromatic image, and
    #   show image in a named window 'input image'
    img = cv.imread(name_file, cv.IMREAD_GRAYSCALE)
    cv.imshow('image', img)
    cv.waitKey()  # WAIT FOR KEY PRESSED

    # change ndarray data type to f64
    print('img.dtype:', img.dtype)
    img = img.astype('float64')
    print('img.dtype:', img.dtype)
    cv.imshow('image', img)
    cv.waitKey()  # WAIT FOR KEY PRESSED

    # have to normalize to range [0.0,1.0]
    for m in range(0, img.shape[0]):
        for n in range(0, img.shape[1]):
            img[m][n] /= 255.0
    cv.imshow('image', img)  # now we good, internally float
    cv.waitKey()  # WAIT FOR KEY PRESSED

    # set up kernel matrix in new ndarray
    # see: numpy.org/doc/stable/reference/arrays.ndarray.html
    r = 1  # radius of filter kernel
    kernel = np.array([
        [1, 2, 1],
        [2, 4, 2],
        [1, 2, 1],
    ], np.float64)
    kernel.__imul__(1 / 16)
    print(kernel)  # make sure we got what we wanted

    # well, 2D convolution it is
    f_star_img = img.copy()  # new result image
    offset = 0.0  # see negative parts of filter result
    # (ignoring borders for now)
    for m in range(0 + r, img.shape[0] - r):
        for n in range(0 + r, img.shape[1] - r):

            # now calculate weighted sum for pixel at (m,n)
            #   (2D convolution at pixel position (m,n)
            sum_ = 0.0
            for k in range(-r, +r + 1):
                for l in range(-r, +r + 1):
                    # kernel is matrix with zero-based indexing
                    #   (k,r = 0,1,2 instead of k,r = -1,0,+1)
                    sum_ += kernel[k + r, l + r] * img[m + k, n + l]
            f_star_img[m, n] = sum_ + offset

    cv.imshow('image filtered', f_star_img)
    cv.waitKey()  # WAIT FOR KEY PRESSED

    img_2 = img.copy()

    kernel_row = np.array([
        [1, 2, 1],
    ], np.float64)
    kernel_row.__imul__(1 / 4)

    kernel_col = kernel_row.transpose()

    radius = 1
    sum = 0.0

    print("About to do row and column convolution")

    for r in range(0, img_2.shape[0]):
        for c in range(0, img_2.shape[1] - radius):
            pixel_before = img[c - radius][r] * kernel_row[0][0]
            pixel_current = img[c][r] * kernel_row[0][1]
            pixel_after = img[c + radius][r] * kernel_row[0][2]
            sum = pixel_before + pixel_current + pixel_after
            img_2[r - radius][c - radius] = sum


    for r in range(0, img_2.shape[1]):
        for c in range(0, img_2.shape[0] - radius):
            pixel_before = img[c - radius][r] * kernel_col[0][0]
            pixel_current = img[c][r] * kernel_col[1][0]
            pixel_after = img[c + radius][r] * kernel_col[2][0]
            sum = pixel_before + pixel_current + pixel_after
            img_2[c - radius][r - radius] = sum


    cv.imshow('image filtered', img_2)
    cv.waitKey()  # WAIT FOR KEY PRESSED




if __name__ == '__main__':
    main()
