#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

int N, M;
int ans[10001];

void bfs(int node, vector<int> list[], int visited[]) {
    queue<int> q;
    q.push(node);
    visited[node] = 1;
    int size = 0;
    while(q.size() != 0) {
        node = q.front();
        q.pop();
        size = list[node].size();
        for(int i = 0; i < size; i++) {
            if(visited[list[node][i]] == 0) {
                ans[list[node][i]]++;
                visited[list[node][i]] = 1;
                q.push(list[node][i]);
            }
        }
    }
}

int main() {
    cin >> N >> M;
    int visited[N+1] = {0,};
    vector<int> list[N+1];
    int A = 0, B = 0;
    for (int i = 1; i <= M; i++)
	{
        cin >> A >> B;
		list[A].push_back(B);
	}
    for (int i = 1; i <= N; i++)
	{
		memset(visited, 0, sizeof(int) * (N+1));
		bfs(i, list, visited);
	}
    int max = 0;
    for (int i = 1; i <= N; i++)
	{
		if(max < ans[i]) max = ans[i];
	}
    vector<int> v;
    for (int i = 1; i <= N; i++)
	{
		if(max == ans[i]) {
            v.push_back(i);
        }
	}
    int size = v.size();
    for (int i = 0; i < size; i++)
	{
        cout << v[i];
        if(i == size-1) break;
        cout << " ";
	}
    cout << endl;
}
