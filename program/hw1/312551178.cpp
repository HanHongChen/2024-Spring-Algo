#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <limits>

using namespace std;

struct Point {
    double x, y;
};

double distance(Point p0, Point p1) {
    return pow(p0.x - p1.x, 2) + pow(p0.y - p1.y, 2);
}

double divideAndConquer(vector<Point>& x, int start, int end) {
    int n = end - start + 1;

    if(n <= 3) {
        switch (n) {
            case 3:
                return min(distance(x[start], x[start + 1]), min(distance(x[start + 1], x[start + 2]), distance(x[start], x[start + 2])));
            case 2:
                return distance(x[start], x[start + 1]);
            default:
                return -999.0;
        }
    }
    int mid = n / 2;

    return min(divideAndConquer(x, start, start + mid - 1), divideAndConquer(x, start + mid, end));
}

int main() {
    int numberOfCases;
    cin >> numberOfCases;

    for (int i = 0; i < numberOfCases; i++) {
        double min = numeric_limits<double>::max();
        int numberOfPoints;
        cin >> numberOfPoints;

        vector<Point> pointsSortedByX(numberOfPoints);
        vector<Point> pointsSortedByY(numberOfPoints);

        for (int j = 0; j < numberOfPoints; j++) {
            cin >> pointsSortedByX[j].x >> pointsSortedByX[j].y;
            pointsSortedByY[j] = pointsSortedByX[j];
        }

        sort(pointsSortedByX.begin(), pointsSortedByX.end(), [](Point p0, Point p1) {
            if(p0.x == p1.x)
                return p0.y < p1.y;
            return p0.x < p1.x;
        });

        sort(pointsSortedByY.begin(), pointsSortedByY.end(), [](Point p0, Point p1) {
            if(p0.y == p1.y)
                return p0.x < p1.x;
            return p0.y < p1.y;
        });

        min = std::min(min, divideAndConquer(pointsSortedByX, 0, numberOfPoints - 1));

        if(numberOfPoints <= 1000){
            for(int j = 0; j < numberOfPoints; j++){

                for(int k = j + 1; k < numberOfPoints; k++){
                    min = std::min(min, distance(pointsSortedByY[j], pointsSortedByY[k]));
                }
            }
        }else{
            for(int j = 0; j < numberOfPoints; j++){
                for(int k = j + 1; k < numberOfPoints && k - j <= 8 ; k++){
                    min = std::min(min, distance(pointsSortedByY[j], pointsSortedByY[k]));
                }
            }
        }
        
        // }

        min = sqrt(min);
        cout<<min<<endl;
    }

    return 0;
}