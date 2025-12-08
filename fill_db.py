import requests
import random
import time

API_URL = "http://localhost:8080/api/v1/products/batch"
TOTAL_PRODUCTS_TO_GENERATE = 2000
BATCH_SIZE = 50

CATEGORIES_CONFIG = {
    "Smartphones": {
        "brands": ["Apple", "Samsung", "Xiaomi", "Google", "Huawei", "OnePlus", "Realme"],
        "models": ["iPhone 15", "Galaxy S24", "Redmi Note 13", "Pixel 8", "P60 Pro", "Nord 3", "GT Neo 5"],
        "suffixes": ["Pro", "Ultra", "Plus", "Max", "Lite", "SE", "Pro Max"],
        "specs": ["128GB", "256GB", "512GB", "1TB"],
        "colors": ["Black", "White", "Titanium", "Blue", "Green", "Purple"],
        "price_range": (15000, 180000)
    },
    "Laptops": {
        "brands": ["Apple", "ASUS", "Lenovo", "HP", "Dell", "MSI", "Acer"],
        "models": ["MacBook Air", "MacBook Pro", "ZenBook", "ThinkPad X1", "Pavilion", "XPS 15", "Katana", "Aspire"],
        "suffixes": ["M2", "M3", "OLED", "Gaming", "Pro", "Slim"],
        "specs": ["16GB/512GB", "32GB/1TB", "8GB/256GB", "i7/RTX4060", "Ryzen 7"],
        "colors": ["Space Gray", "Silver", "Midnight", "Black"],
        "price_range": (45000, 350000)
    },
    "TV": {
        "brands": ["Samsung", "LG", "Sony", "Philips", "TCL", "Hisense", "Xiaomi"],
        "models": ["Smart TV", "OLED Evo", "QLED 4K", "NanoCell", "Bravia XR", "Ambilight TV"],
        "suffixes": ["Series 7", "Series 9", "C3", "G3", "A80L"],
        "specs": ["43\"", "50\"", "55\"", "65\"", "75\"", "85\""],
        "colors": ["Black", "Silver"],
        "price_range": (25000, 450000)
    },
    "Audio": {
        "brands": ["JBL", "Sony", "Apple", "Marshall", "Sennheiser", "Bose", "Samsung"],
        "models": ["Charge 5", "Flip 6", "WH-1000XM5", "AirPods Pro", "Major IV", "Momentum 4", "Galaxy Buds"],
        "suffixes": ["Wireless", "NC", "True Wireless", "Portable", "II", "3"],
        "specs": ["BT 5.3", "Hi-Res", "Bass Boost"],
        "colors": ["Black", "White", "Red", "Blue", "Beige"],
        "price_range": (3000, 45000)
    },
    "Home Appliances": {
        "brands": ["Dyson", "Bosch", "Philips", "DeLonghi", "Tefal", "LG", "Samsung", "Roborock"],
        "models": ["V15 Detect", "Gen 5", "Series 8000", "Magnifica", "Optigrill", "WashTower", "S8 Pro Ultra"],
        "suffixes": ["Absolute", "Animal", "Elite", "Smart", "Wi-Fi"],
        "specs": ["Automatic", "Silent", "Turbo"],
        "colors": ["Grey", "White", "Steel", "Black"],
        "price_range": (8000, 120000)
    },
    "Cameras": {
        "brands": ["Canon", "Sony", "Nikon", "Fujifilm", "GoPro", "DJI"],
        "models": ["EOS R6", "Alpha 7 IV", "Z8", "X-T5", "HERO 12", "Osmo Action 4"],
        "suffixes": ["Mark II", "Mark III", "Black", "Combo"],
        "specs": ["4K 60fps", "24MP", "Full Frame"],
        "colors": ["Black"],
        "price_range": (35000, 250000)
    }
}

DESC_TEMPLATES = [
    "Официальная гарантия. Идеальный выбор для повседневных задач. Высокая производительность и стильный дизайн.",
    "Новинка 2024 года. Улучшенная энергоэффективность и премиальные материалы корпуса.",
    "Хит продаж в категории {category}. Поддержка всех современных стандартов и технологий.",
    "Топовое устройство от {brand}. Невероятное качество и надежность, проверенная временем.",
    "Отличный подарок для себя или близких. Эргономичный дизайн и простота использования."
]

def generate_single_product():
    category_name = random.choice(list(CATEGORIES_CONFIG.keys()))
    cat_data = CATEGORIES_CONFIG[category_name]

    brand = random.choice(cat_data["brands"])

    model = random.choice(cat_data["models"])

    if brand.lower() in model.lower():
        base_name = model
    else:
        base_name = f"{brand} {model}"

    parts = [base_name]
    if random.random() > 0.3: parts.append(random.choice(cat_data["suffixes"]))
    if random.random() > 0.2: parts.append(random.choice(cat_data["specs"]))
    if random.random() > 0.4: parts.append(random.choice(cat_data["colors"]))

    full_name = " ".join(parts)

    min_p, max_p = cat_data["price_range"]
    price = random.randint(min_p // 100, max_p // 100) * 100
    if random.random() > 0.5:
        price -= 10

    description = random.choice(DESC_TEMPLATES).format(category=category_name, brand=brand)

    return {
        "name": full_name,
        "description": description,
        "price": float(price),
        "brand": brand,
        "category": category_name
    }

def main():
    print(f"Начинаем генерацию {TOTAL_PRODUCTS_TO_GENERATE} товаров...")
    start_time = time.time()

    batch = []
    total_sent = 0

    for i in range(TOTAL_PRODUCTS_TO_GENERATE):
        product = generate_single_product()
        batch.append(product)

        if len(batch) == BATCH_SIZE or i == TOTAL_PRODUCTS_TO_GENERATE - 1:
            try:
                response = requests.post(API_URL, json=batch)

                if response.status_code == 201:
                    total_sent += len(batch)
                    print(f"Загружено: {total_sent}/{TOTAL_PRODUCTS_TO_GENERATE} | Последний: {batch[-1]['name']}")
                else:
                    print(f"Ошибка {response.status_code}: {response.text}")
                    break
            except Exception as e:
                print(f"Ошибка соединения: {e}")
                break

            batch = []
            time.sleep(0.1)

    duration = time.time() - start_time
    print(f"\nЗагружено {total_sent} товаров за {duration:.2f} сек.")

if __name__ == "__main__":
    main()
