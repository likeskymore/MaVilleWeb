// src/api/client.ts
export interface ApiResponse<T> {
  data: T | null;
  error: string | null;
  status: number;
}

const API_BASE_URL = "http://localhost:8080/api"; // or just "" if using proxy

export async function apiRequest<T>(
  path: string,
  options: RequestInit = {}
): Promise<ApiResponse<T>> {
  try {
    const response = await fetch(`${API_BASE_URL}${path}`, {
      headers: {
        "Content-Type": "application/json",
        ...(options.headers || {}),
      },
      credentials: "include", // for cookies/session
      ...options,
    });

    const status = response.status;
    const isJson = response.headers.get("content-type")?.includes("application/json");

    if (!response.ok) {
      const errorData = isJson ? await response.json() : { message: "Unknown error" };
      return {
        data: null,
        error: errorData.message || response.statusText,
        status,
      };
    }

    const data = isJson ? await response.json() : null;
    return { data, error: null, status };
  } catch (err) {
    return { data: null, error: "Network error", status: 0 };
  }
}
