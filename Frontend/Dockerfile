# --- Etapa de build ---
FROM node:20-alpine AS build

# Crear carpeta de trabajo
WORKDIR /app

# Copiar solo package.json y package-lock.json para aprovechar cache
COPY package*.json ./

# Instalar dependencias (Docker cacheará esta capa si package.json no cambia)
RUN npm ci

# Copiar el resto del código
COPY . .

# Build de producción
RUN npx ng build --configuration production

# --- Etapa de producción con nginx ---
FROM nginx:alpine

# Copiar archivos construidos al directorio de nginx
COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Configurar nginx para Angular Routing
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exponer puerto 80
EXPOSE 80

# Comando para iniciar nginx
CMD ["nginx", "-g", "daemon off;"]
