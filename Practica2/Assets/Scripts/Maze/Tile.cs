using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace MazesAndMore
{
    public class Tile : MonoBehaviour
    {
        [Tooltip("Sprite que indica que la celda es de hielo")]
        public SpriteRenderer iceFloor;

        [Tooltip("Sprite que indica que la celda es la salida")]
        public SpriteRenderer goalFloor;

        [Tooltip("Sprite que indica que la celda es el inicio")]
        public SpriteRenderer startFloor;

        public void EnableIce()
        {

        }

        public void DisableIce()
        {

        }

        public void EnableStart()
        {

        }

        public void EnableWestWall()
        {

        }

        private void Start()
        {
#if UNITY_EDITOR
        if (iceFloor == null)
            Debug.Log("Me falta el sprite de hielo");
        if (goalFloor == null)
            Debug.Log("Me falta el sprite de salida");
        if (startFloor == null)
            Debug.Log("Me falta el sprite de inicio");
#endif
        }
    }
}
