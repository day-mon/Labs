# CS 1163 - Advanced Topics - Introduction to Computer Vision
# CV Programming 1 - Basic Operations on Images

# import OpenCV, Numerical Python, and Standard Math
import sys
import cv2 as cv
import numpy as np
import math


# clamps a value in the interval [minimum, maximum]
def clamp(value, minimum, maximum):
    return min(maximum, max(minimum, value))


def main():
    print('CV Programming 1 - Basic Image Manipulations')
    print('Python version: ', sys.version)
    print('OpenCV version: ' + cv.__version__)
    print('NumPy version: ' + np.__version__)
    name_file = 'data/ford_256.png'

    # opening an image, convert to monochromatic image, and
    #   show image in a named window 'input image'
    img = cv.imread(name_file, cv.IMREAD_GRAYSCALE)
    cv.imshow('input image', img)
    cv.waitKey()  # WAIT FOR KEY PRESSED

    # you can show the type of any object in python using type()
    print(type(img))

    # in Python, OpenCV images are loaded into a numpy n-dimension array (ndarray)
    # print shape and type information of ndarray
    print('img.shape:', img.shape)
    print('img.dtype:', img.dtype)
    print('img.strides:', img.strides)

    # ndarray is matrix, ROWS x COLUMNS ('first down', meaning:
    #   we do NOT have the standard image coordinate system)
    # set pixel at position I(7,42) := 0 (top left, row 7, column 42, zero-based indexing)
    #   to BLACK and show image in SAME window named 'input image'
    img[7, 42] = 0
    cv.imshow('input image', img)
    cv.waitKey()  # WAIT

    # can iterate directly over all pixels p \in D using loops
    for m in range(0, img.shape[0]):
        img[m, 42] = 0
    cv.imshow('input image', img)
    cv.waitKey()  # WAIT

    # destroy (close) window and mark image data as unreferenced
    cv.destroyWindow('input image')
    img = None  # set reference counter to zero, garbage collection may occur

    # let's reload image and copy resulting ndarray
    img_0 = cv.imread(name_file, cv.IMREAD_GRAYSCALE)
    img_1 = img_0.copy()
    img_2 = img_0.copy()
    img_3 = img_0.copy()
    img_4 = img_0.copy()
    img_5 = img_0.copy()
    shape = img_0.shape


    # iterate over all pixels p
    for m in range(0, shape[0]):
        for n in range(0, shape[1]):
            # add 64 with byte (range) OVERFLOW (modulo 256)
            img_1[m, n] += 64
            # add 64 and clamp/clip result to interval [0,255]
            img_2[m, n] = clamp(img_2[m, n] + 64, 0, 255)
            # invert the range (max_range - value), X-ray effect
            img_3[m, n] = 255 - img_3[m, n]

    # (create and) show two resulting images, move images
    cv.imshow('image 1', img_1)
    cv.imshow('image 2', img_2)
    cv.imshow('image 3', img_3)
    show_grid_inc = 400
    cv.moveWindow('image 1', 100 + 0 * show_grid_inc, 100)
    cv.moveWindow('image 2', 100 + 1 * show_grid_inc, 100)
    cv.moveWindow('image 3', 100 + 2 * show_grid_inc, 100)
    cv.waitKey()  # WAIT

    # use numpy 'flat iterator' to iterate over matrix entries (pixel values)
    # see also: ndarray.ndenumerate
    mu = np.float64(0)
    num_pixels = np.int32(0)
    for item in img_0.flat:
        mu += item
        num_pixels += 1
    mu /= num_pixels
    mu_int = int(mu)

    print('mean=', mu)
    print('mean_int=', mu_int)

    # show absolute difference from mean
    for m in range(0, shape[0]):
        for n in range(0, shape[1]):
            img_0[m, n] = clamp(abs(img_0[m, n] - mu_int), 0, 255)
    cv.imshow('image 0', img_0)
    cv.waitKey()  # WAIT

    radius = 2
    radius_space = 25

    # starts at the row value of 2
    # and dont go all the way to the end
    for i in range(radius, shape[0] - (radius + 1)):
        # starts at the column value of 2
        # and dont goto the end
        for j in range(radius, shape[1] - (radius + 1)):
            average = 0.0
            for l in range(i - radius, i + (radius + 1)):
                for k in range(j - radius, j + (radius + 1)):
                    average = average + img_4[l][k]
            average /= radius_space
            img_5[l][k] = average

    cv.imshow('image 1', img_5)
    cv.waitKey()


if __name__ == '__main__':
    main()
